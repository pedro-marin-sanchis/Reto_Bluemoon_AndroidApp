package com.uguinformatica.bluemoon.androidapp.domain.usecase

import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ITradesRepository
import javax.inject.Inject

class TradeUseCase @Inject constructor(
    private val tradesRepository: ITradesRepository
) {
    suspend fun getTrades(): List<Trade> {
        return tradesRepository.getTrades()
    }

    suspend fun createTrade() {
        tradesRepository.createTrade()
    }
}