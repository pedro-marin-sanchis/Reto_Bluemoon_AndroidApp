package com.uguinformatica.bluemoon.androidapp.data.sources.remote.api

import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.AddCartItemDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.CartItemDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.CreateOrderDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.CreateTradeDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.LoginDto
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.OrderDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.PasswordDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.ProductDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.SilverTypeDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.TokenDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.TradeDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.UpdateCartItemDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BlueMoonApiService {

    /*@GET("ping")
    suspend fun ping(): String*/
    @POST("login")
    suspend fun login(@Body loginDto: LoginDto): Response<TokenDTO>

    // --- User ---

    @GET("users/me")
    suspend fun getUser(@Header("Authorization") token: String): Response<UserDTO>

    @PUT("users/me")
    suspend fun updateUser(@Header("Authorization") token: String, @Body user: UserDTO): Response<UserDTO>

    @PUT("users/me/password")
    suspend fun updatePassword(@Header("Authorization") token: String, @Body password: PasswordDTO): Response<Unit>

    // --- Cart ---

    @GET("users/me/cart-items")
    suspend fun getCartItems(@Header("Authorization") token: String): Response<List<CartItemDTO>>

    @GET("users/me/cart-items/{id}")
    suspend fun getCartItem(@Header("Authorization") token: String,  @Path("id") productId: Long): Response<CartItemDTO>

    @POST("users/me/cart-items")
    suspend fun addCartItem(@Header("Authorization") token: String, @Body cartItem: AddCartItemDTO): Response<CartItemDTO>

    @PUT("users/me/cart-items/{id}")
    suspend fun updateCartItem(@Header("Authorization") token: String,  @Body cartItem: UpdateCartItemDTO, @Path("id") productId: Long): Response<CartItemDTO>

    @DELETE("users/me/cart-items/{id}")
    suspend fun deleteCartItem(@Header("Authorization") token: String, @Path("id") productId: Long): Response<Unit>

    // --- Orders ---

    @GET("users/me/orders")
    suspend fun getOrders(@Header("Authorization") token: String): Response<List<OrderDTO>>

    @GET("users/me/orders/{id}")
    suspend fun getOrder(@Header("Authorization") token: String, @Path("id") orderId: Long): Response<OrderDTO>

    @POST("orders")
    suspend fun addOrder(@Header("Authorization") token: String, @Body createOrder: CreateOrderDTO): Response<OrderDTO>

    // --- Trades ---
    @GET("users/me/trades")
    suspend fun getTrades(@Header("Authorization") token: String): Response<List<TradeDTO>>

    @GET("users/me/trades/{id}")
    suspend fun getTrade(@Header("Authorization") token: String, @Path("id") tradeId: Long): Response<TradeDTO>

    @POST("trades")
    suspend fun addTrade(@Header("Authorization") token: String, @Body trade: CreateTradeDTO): Response<TradeDTO>

    // --- Products ---

    @GET("products")
    suspend fun getProducts(@Header("Authorization") token: String): Response<List<ProductDTO>>

    // --- Silver Types ---

    @GET("silver-types")
    suspend fun getSilverTypes(@Header("Authorization") token: String): Response<List<SilverTypeDTO>>

}