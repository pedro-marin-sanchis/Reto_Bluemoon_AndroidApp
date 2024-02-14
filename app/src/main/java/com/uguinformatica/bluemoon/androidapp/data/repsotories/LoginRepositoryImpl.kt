package com.uguinformatica.bluemoon.androidapp.data.repsotories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.uguinformatica.bluemoon.androidapp.data.mappers.userLoginToUserLoginDto
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.LoginDto
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.getApiErrorType
import com.uguinformatica.bluemoon.androidapp.dataStore
import com.uguinformatica.bluemoon.androidapp.domain.models.UserLogin
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.BadRequest
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.ErrorType
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Forbidden
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Unauthorized
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ILoginRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    val blueMoonApi: BlueMoonApiService,
    @ApplicationContext val context: Context
) : ILoginRepository {
    override suspend fun login(userLogin: UserLogin): Status<Unit> {

        val userData = userLoginToUserLoginDto(userLogin)

        val response = try {
            blueMoonApi.login(userData)

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        val token = response.body()!!

        context.dataStore.edit { settings ->
            settings[stringPreferencesKey("api_token")] = token.token
        }

        return Status.Success(Unit)
    }

    override suspend fun logout() {
        context.dataStore.edit { settings ->
            settings.remove(stringPreferencesKey("api_token"))
        }
    }

    override suspend fun isLogged(): Boolean {
        val token = context.dataStore.data.map {
            it[stringPreferencesKey("api_token")] ?: ""
        }.first()

        return token != ""
    }
}