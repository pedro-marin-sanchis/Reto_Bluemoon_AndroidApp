package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.domain.models.Tradeable
import com.uguinformatica.bluemoon.androidapp.domain.usecase.TradeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TradeViewModel @Inject constructor(
    private val tradeUseCase: TradeUseCase
) : ViewModel() {

    private var _tradesList = MutableLiveData(listOf<Trade>())

    val tradesList: LiveData<List<Trade>> = _tradesList

    fun fetchTradeList() {

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _tradesList.postValue(tradeUseCase.getTrades())
            }
        }

    }
}