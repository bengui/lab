package com.benguiman.lab.domain

import com.benguiman.lab.network.UserDto


fun transformUserDtoToUser(userDto: UserDto): User {
    return User(
        name = userDto.name,
        lastName = userDto.last_name,
        age = userDto.age
    )
}

fun transformUserDtoToUser(userDtoList: List<UserDto>): List<User> =
    userDtoList.map {
        transformUserDtoToUser(it)
    }
