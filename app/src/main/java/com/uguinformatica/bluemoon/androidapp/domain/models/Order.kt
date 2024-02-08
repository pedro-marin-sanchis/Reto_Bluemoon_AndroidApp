package com.uguinformatica.bluemoon.androidapp.domain.models

import java.util.Date

data class Order(
    var date: Date,
    var address: String,
    var productList: List<ProductOrder>,
    val accepted: Boolean,
    val delivered: Boolean
)
