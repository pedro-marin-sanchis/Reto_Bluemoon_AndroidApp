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
class ProductViewModel @Inject constructor(
    val productsUseCase: ProductUseCase,
    val cartUseCase: CartUseCase
) : ViewModel() {

    private var _productsList = MutableLiveData<UiState<List<Product>>>(UiState.Loading())

    val productList: LiveData<UiState<List<Product>>> = _productsList


    fun fetchProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val cartItemsStatus = productsUseCase.getProducts()

                when (cartItemsStatus) {
                    is Status.Success -> {
                        _productsList.postValue(UiState.Loaded(cartItemsStatus.data))
                    }

                    is Status.Error -> {
                        _productsList.postValue(
                            UiState.Error(
                                ErrorTypeToStringConverterImpl().convert(
                                    cartItemsStatus.error
                                )
                            )
                        )
                    }

                }
            }
        }


    }

    fun addProductToCart(productId: Long, quantity: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                cartUseCase.addProductToCart(productId, quantity)
            }
        }
    }
}