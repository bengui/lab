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
        @UiThread next: (userList: Result<List<User>>) -> Unit
    )

    suspend fun fetchUserListSuspend(action: Action): Result<List<User>>

    suspend fun getMultiSourceUserListSuspend(): Result<List<User>>

    suspend fun getMultiSourceUserListSuspendWithRegularApi(): Result<List<User>>
}