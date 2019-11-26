package com.benguiman.lab.network

import com.google.gson.JsonSyntaxException
import java.io.IOException

interface UserApi {

    @Throws(IOException::class, JsonSyntaxException::class)
    fun fetchUsers(
        success: (userList: UserListDto) -> Unit,
        error: () -> Unit
    )

    @Throws(IOException::class, JsonSyntaxException::class)
    fun getUsers(): UserListDto

    @Throws(IOException::class, JsonSyntaxException::class)
    fun getUser(): UserDto

    @Throws(IOException::class, JsonSyntaxException::class)
    suspend fun getUserListSuspend(): UserListDto

    @Throws(IOException::class, JsonSyntaxException::class)
    suspend fun getUserSuspend(): UserDto

}