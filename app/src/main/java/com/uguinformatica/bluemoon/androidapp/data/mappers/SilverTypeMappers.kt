package com.uguinformatica.bluemoon.androidapp.data.mappers

import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.SilverTypeDTO
import com.uguinformatica.bluemoon.androidapp.domain.models.SilverType

fun silverTypeDtoToSilverType (silverTypeDTO: SilverTypeDTO): SilverType {
    return SilverType(
        currentPrice = silverTypeDTO.currentPrice,
        name = silverTypeDTO.name,
    )
}