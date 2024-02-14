package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status

interface IProductsRepository {
    suspend fun getProducts(): Status<List<Product>>
    suspend fun getProduct(id: Long): Status<Product>
}