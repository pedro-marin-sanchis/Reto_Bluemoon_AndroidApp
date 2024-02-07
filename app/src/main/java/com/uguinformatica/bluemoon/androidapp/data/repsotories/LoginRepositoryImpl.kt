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
import com.uguinformatica.bluemoon.androidapp.dataStore
import com.uguinformatica.bluemoon.androidapp.domain.models.UserLogin
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ILoginRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    val blueMoonApi: BlueMoonApiService,
    @ApplicationContext val context: Context
) : ILoginRepository {
    override suspend fun login(userLogin: UserLogin) {

        val userData = userLoginToUserLoginDto(userLogin)

        val response = blueMoonApi.login(userData)

        if (!response.isSuccessful) {
            // TODO: throw exception
            println(response.errorBody()!!.string())
            println(response.code())

            throw Exception("Error while getting token")
            return
        }

        val token = response.body()!!

        context.dataStore.edit { settings ->
            settings[stringPreferencesKey("api_token")] = token.token
        }
    }
}