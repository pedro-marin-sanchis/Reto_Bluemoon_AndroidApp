package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem

interface ICartRepository {
    fun obtainCartItemsList(): List<CartItem>
}