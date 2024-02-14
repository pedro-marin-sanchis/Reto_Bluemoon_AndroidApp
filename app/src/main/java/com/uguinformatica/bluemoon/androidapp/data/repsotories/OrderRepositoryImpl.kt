package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.orderDtoListToOrderList
import com.uguinformatica.bluemoon.androidapp.data.mappers.orderDtoToOrder
import com.uguinformatica.bluemoon.androidapp.data.mappers.productOrderDtoListToProductList
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.getApiErrorType
import com.uguinformatica.bluemoon.androidapp.domain.models.Order
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.ErrorType
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IOrdersRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    val bluemoonApi: BlueMoonApiService
) : IOrdersRepository {
    override suspend fun getOrders(): Status<List<Order>> {
        val response = try {
            bluemoonApi.getOrders()

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        return Status.Success(orderDtoListToOrderList(response.body()!!))
    }
}