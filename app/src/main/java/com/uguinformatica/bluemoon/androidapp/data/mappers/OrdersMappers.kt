package com.uguinformatica.bluemoon.androidapp.data.mappers

import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.OrderDTO
import com.uguinformatica.bluemoon.androidapp.domain.models.Order

fun orderDtoToOrder(orderDto: OrderDTO): Order {
    return Order(
        date = orderDto.date,
        address = orderDto.address,
        delivered = orderDto.delivered,
        accepted = orderDto.accepted,
        productList = productOrderDtoListToProductList(orderDto.products)
    )
}

fun orderDtoListToOrderList(orderDtoList: List<OrderDTO>): List<Order> {
    return orderDtoList.map { orderDtoToOrder(it) }
}
