package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.cartItemsDtoListToCartItemsList
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.AddCartItemDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.CartItemDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.CreateOrderDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.UpdateCartItemDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ICartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    val blueMoonApi: BlueMoonApiService
) : ICartRepository {
    override suspend fun getCartItems(): List<CartItem> {
        val response = blueMoonApi.getCartItems()

        if (!response.isSuccessful) {
            // TODO: throw exception

            throw Exception("Error while getting cart items")
        }


        return cartItemsDtoListToCartItemsList(response.body()!!)
    }

    override suspend fun deleteCartItem(productId: Long) {
        val response = blueMoonApi.deleteCartItem(productId)

        if (!response.isSuccessful) {
            // TODO: throw exception

            throw Exception("Error while deleting cart item")
        }

    }

    override suspend fun modifyCartItemQuantity(productId: Long, quantity: Int) {
        val response = blueMoonApi.updateCartItem(UpdateCartItemDTO(quantity), productId)

        if (!response.isSuccessful) {
            // TODO: throw exception

            throw Exception("Error while updating cart item")
        }

    }

    override suspend fun checkout() {
        val userResponse = blueMoonApi.getUser()

        if (!userResponse.isSuccessful) {
            throw Exception("Error getting user")
        }

        val response = blueMoonApi.addOrder(CreateOrderDTO(userResponse.body()!!.id!!))

        if (!response.isSuccessful) {

            throw Exception("Error while creating order")
        }

    }

    override suspend fun addProductToCart(productId: Long, quantity: Int) {
        val response = blueMoonApi.addCartItem(AddCartItemDTO(productId, quantity))

        if (!response.isSuccessful) {

            // TODO: throw exception
            throw Exception("Error while adding product to cart")
        }
    }
}