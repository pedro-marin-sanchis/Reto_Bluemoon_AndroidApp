package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.silverTypeDtoListToSilverTypeList
import com.uguinformatica.bluemoon.androidapp.data.mappers.tradeCreateToCreateTradeDTO
import com.uguinformatica.bluemoon.androidapp.data.mappers.tradeDtoListToTradeList
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.getApiErrorType
import com.uguinformatica.bluemoon.androidapp.domain.models.SilverType
import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.domain.models.TradeCreate
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.ErrorType
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ITradesRepository
import javax.inject.Inject

class TradesRepositoryImpl @Inject constructor(
    private val blueMoonApi: BlueMoonApiService
): ITradesRepository {
    override suspend fun getTrades(): Status<List<Trade>> {
        val response = try {
            blueMoonApi.getTrades()

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        println(response.body())

        return Status.Success(tradeDtoListToTradeList(response.body()!!))

    }

    override suspend fun createTrade(trade: TradeCreate): Status<Unit> {
        val response = try {
            blueMoonApi.addTrade(tradeCreateToCreateTradeDTO(trade))

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        return Status.Success(Unit)
    }

    override suspend fun getSilverTypes(): Status<List<SilverType>> {
        val response = try {
            blueMoonApi.getSilverTypes()

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        return Status.Success(silverTypeDtoListToSilverTypeList(response.body()!!))
    }
}