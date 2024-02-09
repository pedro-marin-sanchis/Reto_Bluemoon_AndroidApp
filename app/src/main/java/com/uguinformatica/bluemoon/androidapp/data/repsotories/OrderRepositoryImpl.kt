package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.orderDtoListToOrderList
import com.uguinformatica.bluemoon.androidapp.data.mappers.orderDtoToOrder
import com.uguinformatica.bluemoon.androidapp.data.mappers.productOrderDtoListToProductList
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.domain.models.Order
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IOrdersRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    val bluemoonApi: BlueMoonApiService
) : IOrdersRepository {
    override suspend fun getOrders(): List<Order> {
        val response = bluemoonApi.getOrders()

        if (!response.isSuccessful) {
            // TODO: Handle error


            throw Exception("Error getting orders")
        }

        return orderDtoListToOrderList(response.body()!!)
    }
}