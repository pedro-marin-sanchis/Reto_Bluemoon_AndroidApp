package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status

interface ICartRepository {
    suspend fun getCartItems(): Status<List<CartItem>>
    suspend fun deleteCartItem(productId: Long): Status<Unit>
    suspend fun modifyCartItemQuantity(productId: Long, quantity: Int): Status<Unit>
    suspend fun checkout(): Status<Unit>

    suspend fun addProductToCart(productId: Long, quantity: Int): Status<Unit>

}