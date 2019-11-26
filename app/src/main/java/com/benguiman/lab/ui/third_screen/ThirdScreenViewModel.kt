package com.benguiman.lab.ui.third_screen

import android.util.Log
import androidx.lifecycle.*
import com.benguiman.lab.domain.Failure
import com.benguiman.lab.domain.Success
import com.benguiman.lab.domain.UserManager
import com.benguiman.lab.ui.UserUi
import javax.inject.Inject

class ThirdScreenViewModel @Inject constructor(private val userManager: UserManager) : ViewModel() {

    private val requestData = MutableLiveData<UserManager.Action>()
    val errorBannerMessage = MutableLiveData<String>()

    val users: LiveData<List<UserUi>> =
        Transformations.switchMap(requestData) { action ->
            liveData {
                try {
                    when (val result = userManager.fetchUserListSuspend(action)) {
                        is Success -> {
                            emit(result.value)
                            Log.d("LabApp", "Success")
                        }
                        is Failure -> {
                            errorBannerMessage.value =
                                "There was an error loading the users ${result.error.message}"
                            Log.d("LabApp", "Failure")
                        }
                    }

                } catch (throwable: Throwable) {
                    Log.d("LabApp", throwable.message)
                }
            }
        }

    fun loadUsers() {
        requestData.value = UserManager.Action.FORCE_FETCH_USERS
    }

    fun clearUsers() {
        requestData.value = UserManager.Action.CLEAR
    }

}