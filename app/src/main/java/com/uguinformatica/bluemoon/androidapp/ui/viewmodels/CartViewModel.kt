package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class CartViewModel : ViewModel() {

    private var _cartItemsList = MutableLiveData(listOf<CartItem>())
    private var _quantity = MutableLiveData("")
    private var _openAlertDialogDelete = MutableLiveData(false)
    private var _openModifyQuantityDialog = MutableLiveData(false)
    private var _openAlertDialogConfirm = MutableLiveData(false)

    val cartItemsList: LiveData<List<CartItem>> = _cartItemsList
    val quantity: LiveData<String> = _quantity
    val openAlertConfirm: LiveData<Boolean> = _openAlertDialogConfirm
    val openAlertDelete: LiveData<Boolean> = _openAlertDialogDelete
    val openDialogModify: LiveData<Boolean> = _openModifyQuantityDialog

    fun setQuantity(quantity: String) {
        _quantity.value = quantity
    }

    fun openDeleteDialog() {
        _openAlertDialogDelete.value = true
    }

    fun openConfirmDialog() {
        _openAlertDialogConfirm.value = true
    }

    fun openModifyDialog() {
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