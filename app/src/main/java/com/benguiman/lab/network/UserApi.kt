package com.benguiman.lab.network

import java.io.IOException

interface UserApi {

    fun fetchUsers(
        success: (userList: UserListDto) -> Unit,
        error: () -> Unit
    )

    fun getUsers(): UserListDto

    fun getUser(): UserDto

    @Throws(IOException::class)
    suspend fun getUserListSuspend(): UserListDto

    @Throws(IOException::class)
    suspend fun getUserSuspend(): UserDto

}