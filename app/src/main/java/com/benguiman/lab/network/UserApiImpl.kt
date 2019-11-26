package com.benguiman.lab.network

import android.util.Log
import com.android.volley.Response
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
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

    override fun getUsers(): UserListDto {
        //This is an extra delay to demonstrate the cache effect
        Thread.sleep(3000)
        val request = Request.Builder()
            .url(USERS_URL)
            .build()
        val response = client.newCall(request).execute()
        return gson.fromJson(response.body!!.string(), UserListDto::class.java)
    }

    override fun getUser(): UserDto {
        val request = Request.Builder()
            .url(SINGLE_USER_URL)
            .build()
        val response = client.newCall(request).execute()
        return gson.fromJson(response.body!!.string(), UserDto::class.java)
    }

    override suspend fun getUserListSuspend(): UserListDto {
        val request = Request.Builder()
            .url(USERS_URL)
            .build()
        val response = client.newCall(request).execute()
        return gson.fromJson(response.body!!.string(), UserListDto::class.java)
    }

    override suspend fun getUserSuspend(): UserDto {
        val request = Request.Builder()
            .url(SINGLE_USER_URL)
            .build()
        val response = client.newCall(request).execute()
        return gson.fromJson(response.body!!.string(), UserDto::class.java)
    }
}