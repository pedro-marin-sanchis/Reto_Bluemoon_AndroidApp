package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SimulationViewModel : ViewModel() {

    private var _weight = MutableLiveData("")
    private var _description = MutableLiveData("")
    private var _sellPrice = MutableLiveData("")
    private var _openAlertDialog = MutableLiveData(false)
    private var _openAddItemDialog = MutableLiveData(false)

    val weight: LiveData<String> = _weight
    val description: LiveData<String> = _description
    val sellPrice: LiveData<String> = _sellPrice
    val openAlertDialog:LiveData<Boolean> = _openAlertDialog
    val openAddItemDialog: LiveData<Boolean> = _openAddItemDialog

    fun setWeight(weight: String) {
        _weight.value = weight
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setSellPrice(sellPrice: String) {
        _sellPrice.value = sellPrice
    }

    fun changeOpenAlertDialog(openAlertDialog: Boolean) {
        _openAlertDialog.value = !openAlertDialog
    }

    fun changeOpenAddItemDialog(openAddItemDialog: Boolean) {
        _openAddItemDialog.value = !openAddItemDialog
    }
}