package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguinformatica.bluemoon.androidapp.domain.models.Order
import dagger.hilt.android.lifecycle.HiltViewModel

//@HiltViewModel
class OrderViewModel : ViewModel() {

    private var _ordersList = MutableLiveData(listOf<Order>())

    val ordersList: LiveData<List<Order>> = _ordersList

}