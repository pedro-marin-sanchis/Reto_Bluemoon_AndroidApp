package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.User

interface IUserRepository {
    suspend fun getUser(): User

    suspend fun updateUser(user: User)

    suspend fun updateUserPassword(password: String)
}