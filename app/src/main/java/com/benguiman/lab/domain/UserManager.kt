package com.benguiman.lab.domain

import com.benguiman.lab.ui.UserUi
import java.util.concurrent.Future

interface UserManager {

    enum class Action {
        FETCH_USERS, FORCE_FETCH_USERS, CLEAR
    }

    fun fetchUserList(
        success: (userList: List<User>) -> Unit,
        error: () -> Unit
    )

    fun fetchUserListAsync(
        action: UserManager.Action,
        success: (userList: List<UserUi>) -> Unit,
        error: () -> Unit
    )

    suspend fun fetchUserListSuspend(action: Action): Result<List<UserUi>>

    suspend fun getMultiSourceUserListSuspend(): Result<List<UserUi>>

    suspend fun getMultiSourceUserListSuspendWithRegularApi(): Result<List<UserUi>>
}