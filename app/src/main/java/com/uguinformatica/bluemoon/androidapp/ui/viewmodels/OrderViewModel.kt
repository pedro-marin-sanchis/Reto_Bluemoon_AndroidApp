package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.Order
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.usecase.OrderUseCase
import com.uguinformatica.bluemoon.androidapp.ui.ErrorTypeToStringConverterImpl
import com.uguinformatica.bluemoon.androidapp.ui.components.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    val ordersUseCase: OrderUseCase
) : ViewModel() {

    private var _ordersList = MutableLiveData<UiState<List<Order>>>(UiState.Loading())
    private var _toastMessage = MutableLiveData<String>()

    val ordersList: LiveData<UiState<List<Order>>> = _ordersList
    val toastMessage: LiveData<String> = _toastMessage


    fun getOrders() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val ordersStatus = ordersUseCase.getOrders()
                when (ordersStatus) {
                    is Status.Success -> {
                        _ordersList.postValue(UiState.Loaded(ordersStatus.data))
                    }
                    is Status.Error -> {
                        _ordersList.postValue(UiState.Error(ErrorTypeToStringConverterImpl().convert(ordersStatus.error)))
                    }
                }
            }
        }
    }

    fun setToastMessage(message: String) {
        _toastMessage.postValue(message)
    }

}