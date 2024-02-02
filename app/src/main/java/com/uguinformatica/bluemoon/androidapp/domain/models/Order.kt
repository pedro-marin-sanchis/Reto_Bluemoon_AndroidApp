package com.uguinformatica.bluemoon.androidapp.domain.models

data class Order(var date: String, var address: String, var productList: List<Product>)
