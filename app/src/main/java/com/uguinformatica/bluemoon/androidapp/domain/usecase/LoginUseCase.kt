package com.uguinformatica.bluemoon.androidapp.domain.usecase

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.asLiveData
import com.uguinformatica.bluemoon.androidapp.dataStore
import com.uguinformatica.bluemoon.androidapp.domain.models.UserLogin
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ILoginRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    val loginRepository: ILoginRepository,
    @ApplicationContext val context: Context
) {
    suspend fun login(userLoginData: UserLogin) {

        try {
            loginRepository.login(userLoginData)

        } catch (e: Exception) {
            println("Error al obtener token!!!!")
            throw Exception("Error while getting token")
        }

    }

    suspend fun logout() {
        loginRepository.logout()
    }

    suspend fun isLogged(): Boolean {
        return loginRepository.isLogged()
    }
}