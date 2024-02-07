package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.userDtoToUser
import com.uguinformatica.bluemoon.androidapp.data.mappers.userToUserDto
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
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
            println("ERROR user")
        }

        val userDto = response.body()!!

        return userDtoToUser(userDto)
    }

    override suspend fun updateUser(user: User) {
        val userDto = userToUserDto(user)

        val response = blueMoonApi.updateUser(userDto)

        if (!response.isSuccessful){

            // TODO: throw exception
            println("ERROR update user")
            return
        }


    }

}