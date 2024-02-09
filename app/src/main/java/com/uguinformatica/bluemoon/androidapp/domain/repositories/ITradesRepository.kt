package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.Trade

interface ITradesRepository {
    suspend fun getTrades(): List<Trade>
    suspend fun createTrade()
}