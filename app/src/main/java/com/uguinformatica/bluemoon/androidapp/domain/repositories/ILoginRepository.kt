package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.UserLogin
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status

interface ILoginRepository {
    suspend fun login(userLogin: UserLogin): Status<Unit>;
    suspend fun logout();

    suspend fun isLogged(): Boolean;
}