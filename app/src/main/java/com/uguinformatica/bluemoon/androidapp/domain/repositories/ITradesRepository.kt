package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.SilverType
import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.domain.models.TradeCreate

interface ITradesRepository {
    suspend fun getTrades(): List<Trade>
    suspend fun createTrade(trade: TradeCreate)

    suspend fun getSilverTypes(): List<SilverType>
}