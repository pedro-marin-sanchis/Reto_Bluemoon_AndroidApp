package com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO

data class CreateTradeDTO(
    val userId: Long,
    val tradeables: List<CreateTradeableDTO>
)
