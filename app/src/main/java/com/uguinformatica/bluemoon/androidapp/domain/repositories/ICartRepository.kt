package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem

interface ICartRepository {
    suspend fun getCartItems(): List<CartItem>
    suspend fun deleteCartItem(productId: Long)
    suspend fun modifyCartItemQuantity(productId: Long, quantity: Int)
    suspend fun checkout()

}