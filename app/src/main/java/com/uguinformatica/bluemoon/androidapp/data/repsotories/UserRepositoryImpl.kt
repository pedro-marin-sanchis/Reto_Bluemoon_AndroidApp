package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.createUserToCreateUserDto
import com.uguinformatica.bluemoon.androidapp.data.mappers.userDtoToUser
import com.uguinformatica.bluemoon.androidapp.data.mappers.userToUserDto
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.PasswordDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.getApiErrorType
import com.uguinformatica.bluemoon.androidapp.domain.models.CreateUser
import com.uguinformatica.bluemoon.androidapp.domain.models.User
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.ErrorType
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IUserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    val blueMoonApi: BlueMoonApiService
) : IUserRepository {
    override suspend fun getUser(): Status<User> {

        val response = try {
            blueMoonApi.getUser()

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful){
            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        val userDto = response.body()!!

        return Status.Success(userDtoToUser(userDto))
    }

    override suspend fun createUser(user: CreateUser): Status<Unit> {
        val userDto = createUserToCreateUserDto(user)

        val response = try {
            blueMoonApi.createUser(userDto)

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful){

            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        return Status.Success(Unit)
    }

    override suspend fun updateUser(user: User): Status<Unit> {
        val userDto = userToUserDto(user)

        val response = try {
            blueMoonApi.updateUser(userDto)

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful){

            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        return Status.Success(Unit)
    }

    override suspend fun updateUserPassword(password: String): Status<Unit> {
        val passwordDTO = PasswordDTO(password)

        val response = try {
            blueMoonApi.updatePassword(passwordDTO)

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))

        }

        return Status.Success(Unit)
    }

    override suspend fun getBalance(): Status<Double> {
        val response = try {
            blueMoonApi.getUser()

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        val userDto = response.body()!!

        return Status.Success(userDto.balance)
    }

}