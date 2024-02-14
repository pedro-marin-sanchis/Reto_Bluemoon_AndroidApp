package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.CreateUser
import com.uguinformatica.bluemoon.androidapp.domain.models.User
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status

interface IUserRepository {
    suspend fun getUser(): Status<User>
    suspend fun createUser(user: CreateUser): Status<Unit>
    suspend fun updateUser(user: User): Status<Unit>
    suspend fun updateUserPassword(password: String): Status<Unit>
    suspend fun getBalance(): Status<Double>
}