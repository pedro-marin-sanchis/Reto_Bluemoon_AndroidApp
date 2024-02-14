package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.Order
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status

interface IOrdersRepository {
    suspend fun getOrders(): Status<List<Order>>
}