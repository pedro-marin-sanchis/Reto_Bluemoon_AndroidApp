package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.Product
import com.uguinformatica.bluemoon.androidapp.domain.usecase.CartUseCase
import com.uguinformatica.bluemoon.androidapp.domain.usecase.ProductUseCase
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

    private var _product = MutableLiveData<Product>()

    val product: LiveData<Product> = _product

    fun fetchProduct(productId: Long){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val cartItems = productsUseCase.getProduct(productId)

                _product.postValue(cartItems)
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