package com.uguinformatica.bluemoon.androidapp.data.mappers

import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.CartItemDTO
import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem

fun cartItemsDtoListToCartItemsList(cartItemsDto: List<CartItemDTO>): List<CartItem> {
    return cartItemsDto.map {
        CartItem(
            quantity = it.quantity,
            product = productDtoToProduct(it.product)
        )
    }
}