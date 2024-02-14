package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.usecase.CartUseCase
import com.uguinformatica.bluemoon.androidapp.ui.ErrorTypeConverter
import com.uguinformatica.bluemoon.androidapp.ui.ErrorTypeToComposablePopupConverterImpl
import com.uguinformatica.bluemoon.androidapp.ui.ErrorTypeToStringConverterImpl
import com.uguinformatica.bluemoon.androidapp.ui.components.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    val cartUseCase: CartUseCase,
) : ViewModel() {

    private var _cartItems = MutableLiveData<UiState<List<CartItem>>>(UiState.Loading())
    private var _dialogQuantity = MutableLiveData<Int>(0)
    private var _openAlertDialogDelete = MutableLiveData(false)
    private var _openModifyQuantityDialog = MutableLiveData(false)
    private var _openAlertDialogConfirm = MutableLiveData(false)
    private val _openToast = MutableLiveData<String>()

    val cartItems: LiveData<UiState<List<CartItem>>> = _cartItems
    val openToast: LiveData<String> = _openToast
    val dialogQuantity: LiveData<Int> = _dialogQuantity
    val openAlertConfirm: LiveData<Boolean> = _openAlertDialogConfirm
    val openAlertDelete: LiveData<Boolean> = _openAlertDialogDelete
    val openDialogModify: LiveData<Boolean> = _openModifyQuantityDialog


    var productIdToDelete: Long? = null

    var productIdToModify: Long? = null


    fun checkout() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val status = cartUseCase.checkout()

                when (status) {
                    is Status.Success -> {
                        _openToast.postValue("Your order has been made successfully!")
                    }
                    is Status.Error -> {
                        _openToast.postValue(ErrorTypeToStringConverterImpl().convert(status.error))
                    }
                }
            }

            fetchCartItems()
        }
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun fetchCartItems() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val status = cartUseCase.getCartItems()

                when (status) {
                    is Status.Success -> {
                        _cartItems.postValue(UiState.Loaded(status.data))
                    }

                    is Status.Error -> {
                        _openToast.postValue(ErrorTypeToStringConverterImpl().convert(status.error))
                    }
                }
            }
        }
    }

    fun deleteCartItem() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (productIdToDelete != null) {
                    val status = cartUseCase.deleteCartItem(productIdToDelete!!)
                    when (status) {
                        is Status.Success -> {
                            _openToast.postValue("The item was removed from the cart")
                        }

                        is Status.Error -> {
                            _openToast.postValue(ErrorTypeToStringConverterImpl().convert(status.error))
                        }
                    }
                    fetchCartItems()
                }
            }
        }
    }

    fun updateCartItemQuantity() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (productIdToModify != null && _dialogQuantity.value != null) {
                    val status = cartUseCase.modifyCartItemQuantity(
                        productIdToModify!!,
                        _dialogQuantity.value!!
                    )

                    if (status is Status.Error) {
                        _openToast.postValue(ErrorTypeToStringConverterImpl().convert(status.error))
                    }
                }

                fetchCartItems()
            }
        }
    }

    fun setOpenToast(message: String) {
        _openToast.value = message
    }

    fun setQuantity(quantity: Int) {
        _dialogQuantity.value = quantity
    }

    fun openDeleteDialog(productId: Long) {
        productIdToDelete = productId
        _openAlertDialogDelete.value = true
    }

    fun openConfirmDialog() {
        _openAlertDialogConfirm.value = true
    }

    fun openModifyDialog(quantity: Int, productId: Long) {
        productIdToModify = productId
        _dialogQuantity.postValue(quantity)
        _openModifyQuantityDialog.value = true
    }

    fun closeDeleteDialog() {
        _openAlertDialogDelete.value = false
    }

    fun closeConfirmDialog() {
        _openAlertDialogConfirm.value = false
    }

    fun closeModifyDialog() {
        _openModifyQuantityDialog.value = false
    }
}