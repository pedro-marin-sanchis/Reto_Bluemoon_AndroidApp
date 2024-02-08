package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.usecase.CartUseCase
import com.uguinformatica.bluemoon.androidapp.domain.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    val cartUseCase: CartUseCase
) : ViewModel() {

    private var _cartItems = MutableLiveData<List<CartItem>>(emptyList())
    private var _dialogQuantity = MutableLiveData<Int>(0)
    private var _openAlertDialogDelete = MutableLiveData(false)
    private var _openModifyQuantityDialog = MutableLiveData(false)
    private var _openAlertDialogConfirm = MutableLiveData(false)

    val cartItems: LiveData<List<CartItem>> = _cartItems
    val dialogQuantity: LiveData<Int> = _dialogQuantity
    val openAlertConfirm: LiveData<Boolean> = _openAlertDialogConfirm
    val openAlertDelete: LiveData<Boolean> = _openAlertDialogDelete
    val openDialogModify: LiveData<Boolean> = _openModifyQuantityDialog

    var productIdToDelete: Long? = null

    var productIdToModify: Long? = null
    var newQuantity: Int? = null


    fun checkout() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                cartUseCase.checkout()
                fetchCartItems()
            }
        }
    }
    fun fetchCartItems() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val cartItems = cartUseCase.getCartItems()

                _cartItems.postValue(cartItems)

            }
        }
    }

    fun deleteCartItem() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (productIdToDelete != null) {
                    cartUseCase.deleteCartItem(productIdToDelete!!)
                    fetchCartItems()
                }

            }
        }
    }

    fun updateCartItemQuantity() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (productIdToModify != null && _dialogQuantity.value != null) {
                    cartUseCase.modifyCartItemQuantity(productIdToModify!!, _dialogQuantity.value!!)
                    fetchCartItems()
                }
            }
        }
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