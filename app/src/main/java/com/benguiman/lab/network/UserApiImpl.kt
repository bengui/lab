package com.benguiman.lab.network

import android.util.Log
import com.android.volley.Response
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject


class UserApiImpl @Inject constructor(
    private val volleyRequestProcessor: VolleyRequestProcessor,
    private val client: OkHttpClient,
    private val gson: Gson
) : UserApi {

    companion object {
        private const val USERS_URL =
            "https://raw.githubusercontent.com/bengui/frankenstein/master/json/users.json"

        private const val SINGLE_USER_URL =
            "https://raw.githubusercontent.com/bengui/frankenstein/master/json/user.json"
    }

    override fun fetchUsers(
        success: (userList: UserListDto) -> Unit,
        error: () -> Unit
    ) {
        volleyRequestProcessor.addToRequestQueue(
            GsonRequest(
                USERS_URL,
                UserListDto::class.java,
                null,
                Response.Listener(success),
                Response.ErrorListener {
                    Log.e(UserApiImpl::class.java.simpleName, it.message)
                    error()
                })
        )
    }

    @Throws(IOException::class, JsonSyntaxException::class)
    private fun fetchUserList(): UserListDto {
        Thread.sleep(2_000) // Simulates network delay
        val request = Request.Builder()
            .url(USERS_URL)
            .build()
        val response = client.newCall(request).execute()
        return gson.fromJson(response.body!!.string(), UserListDto::class.java)
    }

    @Throws(IOException::class, JsonSyntaxException::class)
    private fun fetchUser(): UserDto {
        Thread.sleep(2_000) // Simulates network delay
        val request = Request.Builder()
            .url(SINGLE_USER_URL)
            .build()
        val response = client.newCall(request).execute()
        response.body?.let {
            return gson.fromJson(it.string(), UserDto::class.java)
        } ?: throw IOException()
    }

    override fun getUsers(): UserListDto {
        return fetchUserList()
    }

    override fun getUser(): UserDto {
        return fetchUser()
    }

    override suspend fun getUserListSuspend(): UserListDto {
        return fetchUserList()
    }

    override suspend fun getUserSuspend(): UserDto {
        return fetchUser()
    }
}