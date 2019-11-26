package com.benguiman.lab.domain

import android.util.Log
import androidx.annotation.UiThread
import com.benguiman.lab.network.UserApi
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException
import javax.inject.Inject


class UserManagerImpl @Inject constructor(
    private val userApi: UserApi
) : UserManager {

    override fun fetchUserList(
        success: (userList: List<User>) -> Unit,
        error: () -> Unit
    ) {
        userApi.fetchUsers(
            {
                success(transformUserDtoToUser(it))
            },
            error
        )
    }

    override fun fetchUserListAsync(
        action: UserManager.Action,
        @UiThread next: (userList: Result<List<User>>) -> Unit
    ) {
        doAsync {
            try {
                val userUiList = when (action) {
                    UserManager.Action.FETCH_USERS -> getUserList(userApi)
                    UserManager.Action.FORCE_FETCH_USERS -> getUserList(userApi)
                    UserManager.Action.CLEAR -> listOf()
                }
                uiThread {
                    next(Success(userUiList))
                }
            } catch (exception: Exception) {
                when (exception) {
                    is IOException, is JsonSyntaxException -> {
                        uiThread {
                            next(Failure(exception))
                        }
                    }
                    else -> throw exception
                }
            }
        }
    }

    private fun getUserList(userApi: UserApi) = transformUserDtoToUser(userApi.getUsers())

    override suspend fun fetchUserListSuspend(action: UserManager.Action): Result<List<User>> {
        return withContext(Dispatchers.IO) {
            try {
                Success(
                    when (action) {
                        UserManager.Action.FETCH_USERS -> getUserList(userApi)
                        UserManager.Action.FORCE_FETCH_USERS -> getUserList(userApi)
                        UserManager.Action.CLEAR -> listOf()
                    }
                )
            } catch (exception: Exception) {
                handleNetworkException(exception)
            }
        }
    }

    private fun handleNetworkException(exception: Exception): Failure<List<User>> {
        return when (exception) {
            is IOException, is JsonSyntaxException -> {
                Log.d("UserManager", exception.message)
                Failure(exception)
            }
            else -> throw exception
        }
    }

    override suspend fun getMultiSourceUserListSuspend(): Result<List<User>> {
        return withContext(Dispatchers.IO) {
            try {
                val usersList = getUserListSuspend()
                val user = getUserSuspend()
                val mutableUsersList = usersList.toMutableList()
                mutableUsersList.add(user)
                Success(mutableUsersList.toList())
            } catch (exception: Exception) {
                handleNetworkException(exception)
            }
        }
    }

    @Throws(IOException::class)
    private suspend fun getUserListSuspend(): List<User> {
        return userApi.getUserListSuspend()
            .map(::transformUserDtoToUser)
    }

    @Throws(IOException::class)
    private suspend fun getUserSuspend(): User {
        return transformUserDtoToUser(
            userApi.getUserSuspend()

        )
    }

    override suspend fun getMultiSourceUserListSuspendWithRegularApi(): Result<List<User>> {
        return withContext(Dispatchers.IO) {
            try {
                val usersList = async {
                    transformUserDtoToUser(
                        userApi.getUsers()
                    )
                }
                val user = async {
                    transformUserDtoToUser(
                        userApi.getUser()
                    )
                }
                val mutableUsersList = usersList.await().toMutableList()
                mutableUsersList.add(user.await())
                Success(mutableUsersList.toList())
            } catch (exception: Exception) {
                handleNetworkException(exception)
            }
        }
    }


}

