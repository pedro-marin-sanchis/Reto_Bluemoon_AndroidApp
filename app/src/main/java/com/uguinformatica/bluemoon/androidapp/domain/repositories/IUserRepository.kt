package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.User

interface IUserRepository {
    suspend fun getUser(): User
}