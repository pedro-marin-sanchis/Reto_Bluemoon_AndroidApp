package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.productDtoListToProductList
import com.uguinformatica.bluemoon.androidapp.data.mappers.productDtoToProduct
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IProductsRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    val apiService: BlueMoonApiService
): IProductsRepository {
    override suspend fun getProducts(): List<Product> {
        val response = apiService.getProducts()

        if (!response.isSuccessful) {
            //TODO: handle error
            throw Exception("Error getting products")
        }

        return productDtoListToProductList(response.body()!!)
    }

    override suspend fun getProduct(id: Long): Product {
        val response = apiService.getProduct(id)

        if (!response.isSuccessful) {
            // TODO: handle error

            throw Exception("Error getting product")
        }

        return productDtoToProduct(response.body()!!)
    }
}