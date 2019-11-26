package com.benguiman.lab.ui

import com.benguiman.lab.domain.User

fun transformUserToUserUi(user: User): UserUi =
    UserUi(name = "${user.name} ${user.lastName}")

fun transformUserToUserUi(userList: List<User>): List<UserUi> {
    return userList.map { transformUserToUserUi(it) }
}