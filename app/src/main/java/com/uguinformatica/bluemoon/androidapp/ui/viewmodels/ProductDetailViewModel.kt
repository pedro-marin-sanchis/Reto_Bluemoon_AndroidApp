package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.usecase.CartUseCase
import com.uguinformatica.bluemoon.androidapp.domain.usecase.ProductUseCase
import com.uguinformatica.bluemoon.androidapp.ui.ErrorTypeToStringConverterImpl
import com.uguinformatica.bluemoon.androidapp.ui.components.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    val productsUseCase: ProductUseCase,
    val cartUseCase: CartUseCase
): ViewModel() {

    private var _product = MutableLiveData<UiState<Product>>(UiState.Loading())

    val product: LiveData<UiState<Product>> = _product

    fun fetchProduct(productId: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val status = productsUseCase.getProduct(productId)

                when (status) {
                    is Status.Success -> {
                        _product.postValue(UiState.Loaded(status.data))
                    }
                    is Status.Error -> {
                        _product.postValue(UiState.Error(ErrorTypeToStringConverterImpl().convert(status.error)))
                    }
                }

                /*if (status is Status.Success<Product>){
                    _product.postValue(UiState.Loaded(status.data))
                }
                if (status is Status.Error){
                    _product.postValue(UiState.Error(ErrorTypeToStringConverterImpl().convert(status.error)))
                }*/
            }
        }
    }

    fun addProductToCart(productId: Long, quantity: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                cartUseCase.addProductToCart(productId, quantity)
            }
        }
    }

}