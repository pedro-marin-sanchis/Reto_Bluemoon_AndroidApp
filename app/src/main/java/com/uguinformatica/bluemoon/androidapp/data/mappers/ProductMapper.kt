package com.uguinformatica.bluemoon.androidapp.data.mappers

import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.ProdOrderDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.ProductDTO
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.domain.models.ProductOrder

fun productDtoToProduct(productDto: ProductDTO): Product {
    return Product(
        id = productDto.id,
        name = productDto.name,
        price = productDto.price,
        image = productDto.img,
        description = productDto.description
    )
}

fun productDtoListToProductList(productDtoList: List<ProductDTO>): List<Product> {
    return productDtoList.map { productDtoToProduct(it) }
}

fun productOrderDtoToProduct(productDto: ProdOrderDTO): ProductOrder {
    return ProductOrder(
        quantity = productDto.quantity,
        product = productDtoToProduct(productDto.product)
    )
}

fun productOrderDtoListToProductList(productDtoList: List<ProdOrderDTO>): List<ProductOrder> {
    return productDtoList.map { productOrderDtoToProduct(it) }
}