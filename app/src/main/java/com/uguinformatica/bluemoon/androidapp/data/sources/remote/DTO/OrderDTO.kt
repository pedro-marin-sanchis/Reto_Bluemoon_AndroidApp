package com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO

import java.util.Date

data class OrderDTO(
    val id: Int,
    val date: Date,
    val address: String,
    val delivered: Boolean,
    val accepted: Boolean,
    val products: List<ProdOrderDTO>
)