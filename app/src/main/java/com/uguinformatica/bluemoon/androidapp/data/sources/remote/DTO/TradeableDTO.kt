package com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO

data class TradeableDTO (
    val id: Int,
    val weight: Double,
    val sellPrice: Double,
    val description: String,
    val silverType: SilverTypeDTO
)