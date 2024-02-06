package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.Order

interface IObtainOrdersRemote {
    fun obtainOrdersList(): List<Order>
}