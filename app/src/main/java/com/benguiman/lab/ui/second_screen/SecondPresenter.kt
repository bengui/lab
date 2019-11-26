package com.benguiman.lab.ui.second_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.benguiman.lab.domain.Failure
import com.benguiman.lab.domain.Success
import com.benguiman.lab.domain.UserManager
import com.benguiman.lab.ui.Navigator
import com.benguiman.lab.ui.UserUi
import javax.inject.Inject

class SecondPresenter @Inject constructor(
    private val navigator: Navigator,
    private val userManager: UserManager
) {
    val errorBannerMessage = MutableLiveData<String>()

    private val _userUiLiveData: MutableLiveData<List<UserUi>> = MutableLiveData()
    val userUiLiveData: LiveData<List<UserUi>>
        get() = _userUiLiveData

    fun thirdFragmentButtonClick() {
        navigator.navigateToThirdScreen()
    }

    fun displayUserList() {
        fetchUserData(UserManager.Action.FETCH_USERS)
    }

    fun forceFetchUsersList() {
        fetchUserData(UserManager.Action.FORCE_FETCH_USERS)
    }

    fun clearUserList() {
        fetchUserData(UserManager.Action.CLEAR)
    }

    private fun fetchUserData(action: UserManager.Action) {
        userManager.fetchUserListAsync(action) {
            when (it) {
                is Success -> _userUiLiveData.value = it.value
                is Failure -> errorBannerMessage.value = "Error: ${it.error.message}"
            }
        }

    }

    fun displayError() {
        errorBannerMessage.value = "Error displayed by pressing the button"
    }

}