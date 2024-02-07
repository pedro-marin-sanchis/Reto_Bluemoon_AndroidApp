package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.Product

interface IProductsRepository {
    fun obtainProductList(): List<Product>
    fun obtainProduct(id: Int): Product
}