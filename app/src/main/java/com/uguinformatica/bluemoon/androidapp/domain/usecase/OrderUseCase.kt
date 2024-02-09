package com.uguinformatica.bluemoon.androidapp.domain.usecase

import com.uguinformatica.bluemoon.androidapp.domain.models.Order
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IOrdersRepository
import javax.inject.Inject

class OrderUseCase @Inject constructor(
    val ordersRepository: IOrdersRepository
) {
    suspend fun getOrders(): List<Order> {
        return ordersRepository.getOrders()

    }

}