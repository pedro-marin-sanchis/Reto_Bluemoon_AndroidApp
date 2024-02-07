package com.uguinformatica.bluemoon.androidapp.data.mappers

import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.ProductDTO
import com.uguinformatica.bluemoon.androidapp.domain.models.Product

fun productDtoToProduct(productDto: ProductDTO): Product {
    return Product(
        id = productDto.id,
        name = productDto.name,
        price = productDto.price,
        image = productDto.img,
        description = productDto.description
    )
}