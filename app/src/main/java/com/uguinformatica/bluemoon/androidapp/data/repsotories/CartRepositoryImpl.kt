package com.uguinformatica.bluemoon.androidapp.data.repsotories

import com.uguinformatica.bluemoon.androidapp.data.mappers.cartItemsDtoListToCartItemsList
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.AddCartItemDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.CreateOrderDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.UpdateCartItemDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.BlueMoonApiService
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.api.getApiErrorType
import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.ErrorType
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ICartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    val blueMoonApi: BlueMoonApiService
) : ICartRepository {
    override suspend fun getCartItems(): Status<List<CartItem>> {
        val response = try {
            blueMoonApi.getCartItems()

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {

            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }


        return Status.Success(cartItemsDtoListToCartItemsList(response.body()!!))
    }

    override suspend fun deleteCartItem(productId: Long): Status<Unit> {
        val response = try {
            blueMoonApi.deleteCartItem(productId)

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        return Status.Success(Unit)
    }

    override suspend fun modifyCartItemQuantity(productId: Long, quantity: Int): Status<Unit> {
        val response = try {
            blueMoonApi.updateCartItem(UpdateCartItemDTO(quantity), productId)

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {
            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        return Status.Success(Unit)
    }

    override suspend fun checkout(): Status<Unit> {
        val userResponse = try {
            blueMoonApi.getUser()

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!userResponse.isSuccessful) {
            return Status.Error(
                getApiErrorType(
                    userResponse.code(),
                    userResponse.errorBody()!!.string()
                )
            )
        }

        val response = blueMoonApi.addOrder(CreateOrderDTO(userResponse.body()!!.id!!))

        if (!response.isSuccessful) {

            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        return Status.Success(Unit)
    }

    override suspend fun addProductToCart(productId: Long, quantity: Int): Status<Unit> {
        val response = try {
            blueMoonApi.addCartItem(AddCartItemDTO(productId, quantity))

        } catch (e: Exception) {
            return Status.Error(ErrorType.Api.Network)
        }

        if (!response.isSuccessful) {

            return Status.Error(getApiErrorType(response.code(), response.errorBody()!!.string()))
        }

        return Status.Success(Unit)
    }
}