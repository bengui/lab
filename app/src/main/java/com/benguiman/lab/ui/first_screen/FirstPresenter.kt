package com.benguiman.lab.ui.first_screen

import com.benguiman.lab.domain.UserManager
import com.benguiman.lab.ui.Navigator
import com.benguiman.lab.ui.transformUserToUserUi
import javax.inject.Inject

class FirstPresenter @Inject constructor(
    private val view: FirstView,
    private val navigator: Navigator,
    private val userManager: UserManager
) {
    fun secondFragmentButtonClick() {
        navigator.navigateToSecondScreen()
    }

    fun displayUserList() {
        userManager.fetchUserList(
            {
                view.printUserList(transformUserToUserUi(it))
            },
            this@FirstPresenter::onError
        )
    }

    private fun onError() {

    }

    fun clearUserList() {
        view.printUserList(listOf())
    }
}