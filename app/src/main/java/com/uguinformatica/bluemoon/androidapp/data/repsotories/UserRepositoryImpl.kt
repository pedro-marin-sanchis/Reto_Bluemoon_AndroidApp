package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.createUserToCreateUserDto
import com.uguinformatica.bluemoon.androidapp.data.mappers.userDtoToUser
import com.uguinformatica.bluemoon.androidapp.data.mappers.userToPasswordDto
import com.uguinformatica.bluemoon.androidapp.data.mappers.userToUserDto
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.PasswordDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.domain.models.CreateUser
import com.uguinformatica.bluemoon.androidapp.domain.models.User
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IUserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    val blueMoonApi: BlueMoonApiService
) : IUserRepository {
    override suspend fun getUser(): User {

        val response = blueMoonApi.getUser()

        if (!response.isSuccessful){
            // TODO: throw exception

            throw Exception("Error while getting user")
        }

        val userDto = response.body()!!

        return userDtoToUser(userDto)
    }

    override suspend fun createUser(user: CreateUser) {
        val userDto = createUserToCreateUserDto(user)

        val response = blueMoonApi.createUser(userDto)

        println("creating user...")

        if (!response.isSuccessful){

            println("error while creating user")
            println(response.errorBody())
            println(response.code())

            throw Exception("Error while creating user")
        }

        println("user created")
    }

    override suspend fun updateUser(user: User) {
        val userDto = userToUserDto(user)

        val response = blueMoonApi.updateUser(userDto)

        if (!response.isSuccessful){

            // TODO: throw exception

            throw Exception("Error while updating user")
        }


    }

    override suspend fun updateUserPassword(password: String) {
        val passwordDTO = PasswordDTO(password)

        val response = blueMoonApi.updatePassword(passwordDTO)

        if (!response.isSuccessful) {
            // TODO: throw exception

            throw Exception("Error while updating password")

        }

    }

}