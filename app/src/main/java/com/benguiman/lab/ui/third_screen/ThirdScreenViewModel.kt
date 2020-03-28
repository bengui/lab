package com.benguiman.lab.ui.third_screen

import androidx.lifecycle.*
import com.benguiman.lab.domain.Failure
import com.benguiman.lab.domain.Success
import com.benguiman.lab.domain.UserManager
import com.benguiman.lab.ui.UserUi
import com.benguiman.lab.ui.transformUserToUserUi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThirdScreenViewModel @Inject constructor(private val userManager: UserManager) : ViewModel() {

    private val requestData = MutableLiveData<UserManager.Action>()
    private val _errorBannerMessage = MutableLiveData<String>()
    val errorBannerMessage: LiveData<String>
        get() = _errorBannerMessage

    private var timerStarted = false

    private val _title = MutableLiveData<String>()
    val subTitle get() = _title

    val users: LiveData<List<UserUi>> =
        Transformations.switchMap(requestData) { action ->
            liveData {
                when (val result = userManager.fetchUserListSuspend(action)) {
                    is Success -> {
                        emit(transformUserToUserUi(result.value))
                    }
                    is Failure -> {
                        _errorBannerMessage.value =
                            "There was an error loading the users ${result.error.message}"
                    }
                }
            }
        }

    fun loadUsers() {
        requestData.value = UserManager.Action.FORCE_FETCH_USERS
    }

    fun clearUsers() {
        requestData.value = UserManager.Action.CLEAR
    }

    fun startSubtitleTimer() {
        if (!timerStarted) {
            timerStarted = true
            viewModelScope.launch {
                var number = 1
                while (number < 100) {
                    delay(1_000)
                    _title.value = number.toString()
                    number++
                }
            }
        }
    }

}