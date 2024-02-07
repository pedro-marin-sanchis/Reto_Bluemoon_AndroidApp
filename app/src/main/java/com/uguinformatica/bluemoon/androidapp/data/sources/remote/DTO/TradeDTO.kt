package com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO

import java.util.Date

data class TradeDTO(
    val id: Int,
    val tradeables: List<TradeableDTO>
)
