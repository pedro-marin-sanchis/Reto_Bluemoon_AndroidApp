package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguinformatica.bluemoon.androidapp.domain.models.SilverType
import com.uguinformatica.bluemoon.androidapp.domain.models.Tradeable
import dagger.hilt.android.lifecycle.HiltViewModel

//@HiltViewModel
class SimulationViewModel : ViewModel() {

    private var _tradeableItemList = MutableLiveData(mutableListOf<Tradeable>())
    private var _tradeableItem = MutableLiveData(Tradeable(0f,"",null, SilverType("",0f)))
    private var _weight = MutableLiveData("")
    private var _description = MutableLiveData("")
    private var _sellPrice = MutableLiveData("")
    private var _silverTypeList = MutableLiveData(listOf<SilverType>())
    private var _openAlertDialog = MutableLiveData(false)
    private var _openAddItemDialog = MutableLiveData(false)
    private var _openModifyItemDialog = MutableLiveData(false)

    val weight: LiveData<String> = _weight
    val description: LiveData<String> = _description
    val sellPrice: LiveData<String> = _sellPrice
    val silverTypeList: LiveData<List<SilverType>> = _silverTypeList
    val openAlertDialog:LiveData<Boolean> = _openAlertDialog
    val openAddItemDialog: LiveData<Boolean> = _openAddItemDialog
    val tradeableItemList: LiveData<MutableList<Tradeable>> = _tradeableItemList
    val tradeableItem: LiveData<Tradeable> = _tradeableItem
    val openModifyItemDialog: LiveData<Boolean> = _openModifyItemDialog

    fun setWeight(weight: String) {
        _weight.postValue(weight)
    }

    fun addTradeable(tradeable: Tradeable) {
        _tradeableItemList.value?.add(tradeable)
    }

    fun setDescription(description: String) {
        _description.postValue(description)
    }

    fun setSellPrice(sellPrice: String) {
        _sellPrice.postValue(sellPrice)
    }

    fun changeOpenAlertDialog(openAlertDialog: Boolean) {
        _openAlertDialog.postValue(!openAlertDialog)
    }

    fun changeOpenAddItemDialog(openAddItemDialog: Boolean) {
        _openAddItemDialog.postValue(!openAddItemDialog)
    }

    fun changeOpenModifyItemDialog(openModifyItemDialog: Boolean, tradeable: Tradeable) {
        _tradeableItem.value = tradeable
        setWeight(tradeable.weight.toString())
        setDescription(tradeable.description)
        _openModifyItemDialog.postValue(!openModifyItemDialog)
    }

    fun modifyTradeable(tradeable: Tradeable) {
        tradeable.weight = _weight.value?.toFloat()!!
        tradeable.description = description.value!!
    }
}