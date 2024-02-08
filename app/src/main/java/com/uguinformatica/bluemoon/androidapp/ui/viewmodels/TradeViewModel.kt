package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.domain.models.Tradeable
import dagger.hilt.android.lifecycle.HiltViewModel

//@HiltViewModel
class TradeViewModel : ViewModel() {

    private var _tradesList = MutableLiveData(listOf<Trade>())

    val tradesList: LiveData<List<Trade>> = _tradesList

}