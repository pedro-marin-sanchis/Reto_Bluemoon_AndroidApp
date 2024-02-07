package com.uguinformatica.bluemoon.androidapp.domain.usecase

import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ICartRepository
import javax.inject.Inject

class CartUseCase @Inject constructor(
    val cartRepository: ICartRepository
) {
    suspend fun getCartItems(): List<CartItem> = cartRepository.getCartItems()

    suspend fun deleteCartItem(productId: Long) = cartRepository.deleteCartItem(productId)

    suspend fun modifyCartItemQuantity(productId: Long, quantity: Int) = cartRepository.modifyCartItemQuantity(productId, quantity)
}