package com.uguinformatica.bluemoon.androidapp.data.mappers

import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.CreateTradeDTO
import com.uguinformatica.bluemoon.androidapp.data.sources.remote.DTO.TradeDTO
import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.domain.models.TradeCreate

fun tradeDtoToTrade (tradeDTO: TradeDTO): Trade {
    return Trade(
        date = tradeDTO.date,
        validated = tradeDTO.validated,
        tradeableList = tradeableaDtoListToTradeableList(tradeDTO.tradeables)
    )
}

fun tradeDtoListToTradeList (tradeDTOList: List<TradeDTO>): List<Trade> {
    return tradeDTOList.map { tradeDtoToTrade(it) }
}

fun tradeCreateToCreateTradeDTO (trade: TradeCreate): CreateTradeDTO {
    return CreateTradeDTO(
        tradeables = tradeableListToCreateTradeableDTOList(trade.tradeables)
    )
}


