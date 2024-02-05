package com.uguinformatica.bluemoon.androidapp.data.sources.remote.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BlueMoonApi {
    val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder().baseUrl("http://192.168.1.169:8080/api/")

        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val retrofitService: BlueMoonApiService by lazy {
        retrofit.create(BlueMoonApiService::class.java)
    }
}