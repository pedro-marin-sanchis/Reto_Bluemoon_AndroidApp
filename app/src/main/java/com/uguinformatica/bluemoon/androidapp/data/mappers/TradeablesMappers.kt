package com.uguinformatica.bluemoon.androidapp.data.mappers

import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.TradeableDTO
import com.uguinformatica.bluemoon.androidapp.domain.models.Tradeable

fun tradeableDtoToTradeable(tradeableDTO: TradeableDTO): Tradeable {
    return Tradeable(
        weight = tradeableDTO.weight,
        sellPrice = tradeableDTO.sellPrice,
        sliverType = silverTypeDtoToSilverType(tradeableDTO.silverType),
        description = tradeableDTO.description

    )
}

fun tradeableaDtoListToTradeableList(tradeableDTOList: List<TradeableDTO>): List<Tradeable> {
    return tradeableDTOList.map { tradeableDtoToTradeable(it) }
}
