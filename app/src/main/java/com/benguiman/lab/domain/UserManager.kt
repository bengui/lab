package com.benguiman.lab.domain

import androidx.annotation.UiThread
import com.benguiman.lab.ui.UserUi

interface UserManager {

    enum class Action {
        FETCH_USERS, FORCE_FETCH_USERS, CLEAR
    }

    fun fetchUserList(
        success: (userList: List<User>) -> Unit,
        error: () -> Unit
    )

    fun fetchUserListAsync(
        action: Action,
        @UiThread next: (userList: Result<List<UserUi>>) -> Unit
    )

    suspend fun fetchUserListSuspend(action: Action): Result<List<UserUi>>

    suspend fun getMultiSourceUserListSuspend(): Result<List<UserUi>>

    suspend fun getMultiSourceUserListSuspendWithRegularApi(): Result<List<UserUi>>
}