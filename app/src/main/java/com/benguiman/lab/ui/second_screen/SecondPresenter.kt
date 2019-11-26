package com.benguiman.lab.ui.second_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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
    val userUiLiveData: LiveData<List<UserUi>> =
        Transformations.map(_userUiLiveData) { userUiList -> userUiList }

    fun secondFragmentButtonClick() {
        navigator.navigateBack()
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
        userManager.fetchUserListAsync(
            action,
            _userUiLiveData::postValue,
            this::displayError
        )
    }

    fun displayError() {
        errorBannerMessage.postValue("Error displayed by pressing the button")
    }

}