package com.uguinformatica.bluemoon.androidapp.domain.usecase

import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IProductsRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    val repository: IProductsRepository
){
    suspend fun getProducts(): Status<List<Product>> = repository.getProducts()
    suspend fun getProduct(id: Long): Status<Product> = repository.getProduct(id)
}