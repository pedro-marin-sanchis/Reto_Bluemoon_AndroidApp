package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel

//@HiltViewModel
class ProductViewModel : ViewModel() {

    private var _productsList = MutableLiveData(listOf<Product>())

    val productList: LiveData<List<Product>> = _productsList

}