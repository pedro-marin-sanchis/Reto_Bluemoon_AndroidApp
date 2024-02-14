package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.domain.models.Tradeable
import com.uguinformatica.bluemoon.androidapp.domain.usecase.TradeUseCase
import com.uguinformatica.bluemoon.androidapp.ui.ErrorTypeToStringConverterImpl
import com.uguinformatica.bluemoon.androidapp.ui.components.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TradeViewModel @Inject constructor(
    private val tradeUseCase: TradeUseCase
) : ViewModel() {

    private var _tradesList = MutableLiveData<UiState<List<Trade>>>(UiState.Loading())

    val tradesList: LiveData<UiState<List<Trade>>> = _tradesList

    fun fetchTradeList() {

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val tradesStatus = tradeUseCase.getTrades()
                when (tradesStatus) {
                    is com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status.Success -> {
                        _tradesList.postValue(UiState.Loaded(tradesStatus.data))
                    }
                    is com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status.Error -> {
                        _tradesList.postValue(UiState.Error(ErrorTypeToStringConverterImpl().convert(tradesStatus.error)))
                    }
                }
            }
        }

    }
}