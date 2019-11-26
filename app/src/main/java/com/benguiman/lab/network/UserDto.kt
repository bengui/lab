package com.benguiman.lab.network

data class UserDto(
    val name: String,
    val last_name: String,
    val age: Int
)

class UserListDto : ArrayList<UserDto>()