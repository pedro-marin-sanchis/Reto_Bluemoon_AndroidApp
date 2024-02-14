package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.productDtoListToProductList
import com.uguinformatica.bluemoon.androidapp.data.mappers.productDtoToProduct
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.ErrorType
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IProductsRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    val apiService: BlueMoonApiService
) : IProductsRepository {
    override suspend fun getProducts(): Status<List<Product>> {
        val response = try {
            apiService.getProducts()

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            if (response.code() == 403) {
                return Status.Error(ErrorType.Api.Unauthorized)
            }

            return Status.Error(ErrorType.Unknown)
        }

        return Status.Success(productDtoListToProductList(response.body()!!))
    }

    override suspend fun getProduct(id: Long): Status<Product> {
        val response = try {
            apiService.getProduct(id)

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            if (response.code() == 403) {
                return Status.Error(ErrorType.Api.Unauthorized)
            }

            if (response.code() == 404) {
                return Status.Error(ErrorType.Api.NotFound)
            }

            return Status.Error(ErrorType.Unknown)
        }

        return Status.Success(productDtoToProduct(response.body()!!))
    }
}