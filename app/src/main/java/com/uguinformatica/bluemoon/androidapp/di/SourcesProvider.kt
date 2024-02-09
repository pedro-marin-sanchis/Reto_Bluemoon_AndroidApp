package com.uguinformatica.bluemoon.androidapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class SourcesProvider {

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    fun providesOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }
    @Provides
    fun providesRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(client)
            .baseUrl("http://192.168.103.165:8080/api/")
            .addConverterFactory(
                GsonConverterFactory
                    .create(gson)
            )
            .build();

    }

    @Provides
    fun providesBlueMoonApi(retrofit: Retrofit): BlueMoonApiService {
        return retrofit.create(BlueMoonApiService::class.java)
    }


}