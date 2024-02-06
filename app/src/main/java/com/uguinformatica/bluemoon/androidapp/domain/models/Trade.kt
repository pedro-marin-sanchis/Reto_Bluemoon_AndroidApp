package com.uguinformatica.bluemoon.androidapp.domain.models

import java.util.Date

data class Trade(var date: Date, var validated: Boolean, var tradeableList: List<Tradeable>)
