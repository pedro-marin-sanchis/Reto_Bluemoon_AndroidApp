package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.UserLogin

interface ILoginRepository {
    suspend fun login(userLogin: UserLogin);
}