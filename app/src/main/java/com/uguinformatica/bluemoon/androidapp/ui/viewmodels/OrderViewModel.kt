package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.Order
import com.uguinformatica.bluemoon.androidapp.domain.usecase.OrderUseCase
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

    private var _ordersList = MutableLiveData(listOf<Order>())

    val ordersList: LiveData<List<Order>> = _ordersList

    fun getOrders() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val orders = ordersUseCase.getOrders()
                _ordersList.postValue(orders)
            }
        }
    }

}