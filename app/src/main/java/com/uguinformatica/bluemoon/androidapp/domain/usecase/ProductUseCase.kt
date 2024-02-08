package com.uguinformatica.bluemoon.androidapp.domain.usecase

import com.uguinformatica.bluemoon.androidapp.domain.repositories.IProductsRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    val repository: IProductsRepository
){
    suspend fun getProducts() = repository.getProducts()
    suspend fun getProduct(id: Long) = repository.getProduct(id)
}