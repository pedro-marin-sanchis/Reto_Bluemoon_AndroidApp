package com.uguinformatica.bluemoon.androidapp.data.mappers

import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.LoginDto
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.UserDTO
import com.uguinformatica.bluemoon.androidapp.domain.models.User
import com.uguinformatica.bluemoon.androidapp.domain.models.UserLogin

fun userDtoToUser(userDTO: UserDTO): User {
    return User(
        userName = userDTO.username,
        name = userDTO.name,
        address = userDTO.address,
        surnames = userDTO.surnames,
        email = userDTO.email,
        balance = userDTO.balance
    )
}

fun userLoginToUserLoginDto(userLogin: UserLogin): LoginDto {
    return LoginDto(
        username = userLogin.username,
        password = userLogin.password
    )
}