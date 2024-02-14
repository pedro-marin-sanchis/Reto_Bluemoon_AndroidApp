package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.SilverType
import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.domain.models.TradeCreate
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status

interface ITradesRepository {
    suspend fun getTrades(): Status<List<Trade>>
    suspend fun createTrade(trade: TradeCreate): Status<Unit>

    suspend fun getSilverTypes(): Status<List<SilverType>>
}