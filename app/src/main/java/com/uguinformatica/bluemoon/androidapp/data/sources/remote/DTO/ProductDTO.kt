package com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO

data class ProductDTO (
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val img: String,
    val disabled: Boolean
)