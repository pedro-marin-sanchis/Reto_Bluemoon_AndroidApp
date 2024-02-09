package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.Product

interface IProductsRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProduct(id: Long): Product
}