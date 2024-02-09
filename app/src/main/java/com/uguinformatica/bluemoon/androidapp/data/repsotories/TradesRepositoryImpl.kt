package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.tradeDtoListToTradeList
import com.uguinformatica.bluemoon.androidapp.data.mappers.tradeableaDtoListToTradeableList
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ITradesRepository
import javax.inject.Inject

class TradesRepositoryImpl @Inject constructor(
    private val blueMoonApi: BlueMoonApiService
): ITradesRepository {
    override suspend fun getTrades(): List<Trade> {
        val response = blueMoonApi.getTrades()

        if (!response.isSuccessful) {
            println(response.errorBody())
            throw Exception("Error getting trades")
        }

        println(response.body())

        return tradeDtoListToTradeList(response.body()!!)

    }

    override suspend fun createTrade() {
        TODO("Not yet implemented")
    }
}