package com.uguinformatica.bluemoon.androidapp.domain.usecase

import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ICartRepository
import javax.inject.Inject

class CartUseCase @Inject constructor(
    val cartRepository: ICartRepository
) {
    suspend fun getCartItems(): Status<List<CartItem>> = cartRepository.getCartItems()

    suspend fun deleteCartItem(productId: Long): Status<Unit> = cartRepository.deleteCartItem(productId)

    suspend fun modifyCartItemQuantity(productId: Long, quantity: Int): Status<Unit> = cartRepository.modifyCartItemQuantity(productId, quantity)

    suspend fun checkout(): Status<Unit> = cartRepository.checkout()

    suspend fun addProductToCart(productId: Long, quantity: Int): Status<Unit> = cartRepository.addProductToCart(productId, quantity)
}