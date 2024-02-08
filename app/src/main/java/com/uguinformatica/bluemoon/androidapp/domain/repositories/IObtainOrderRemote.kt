package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.Order
import com.uguinformatica.bluemoon.androidapp.domain.models.OrderItem

interface IObtainOrderRemote {
    fun obtainOrderList(): List<Order>
    fun obtainOrderItemList(): List<OrderItem>
}