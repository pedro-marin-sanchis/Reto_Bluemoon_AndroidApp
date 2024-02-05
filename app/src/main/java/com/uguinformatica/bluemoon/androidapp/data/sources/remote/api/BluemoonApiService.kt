package com.uguinformatica.bluemoon.androidapp.data.sources.remote.api

import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.CartItemDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.LoginDto
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.OrderDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.ProductDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.SilverTypeDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.TokenDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.TradeDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.UserDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface BlueMoonApiService {

    /*@GET("ping")
    suspend fun ping(): String*/
    @POST("login")
    suspend fun login(@Body loginDto: LoginDto): TokenDTO

    @GET("users/me")
    suspend fun getUser(@Header("Authorization") token: String): UserDTO

    /*@PUT("users/me")
    suspend fun updateUser(@Header("Authorization") token: String, @Body user: UserDTO): UserDTO*/

    @GET("users/me/cart-items")
    suspend fun getCartItems(@Header("Authorization") token: String): List<CartItemDTO>

    @GET("users/me/cart-items/{id}")
    suspend fun getCartItem(@Header("Authorization") token: String,  @Path("id") id: Long): CartItemDTO

    @GET("users/me/orders")
    suspend fun getOrders(@Header("Authorization") token: String): List<OrderDTO>

    @GET("users/me/orders/{id}")
    suspend fun getOrder(@Header("Authorization") token: String, @Path("id") id: Long): OrderDTO

    @GET("users/me/trades")
    suspend fun getTrades(@Header("Authorization") token: String): List<TradeDTO>

    @GET("products")
    suspend fun getProducts(@Header("Authorization") token: String): List<ProductDTO>

    @GET("silver-types")
    suspend fun getSilverTypes(@Header("Authorization") token: String): List<SilverTypeDTO>

}