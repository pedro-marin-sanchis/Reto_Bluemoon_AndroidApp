package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.uguinformatica.bluemoon.androidapp.domain.models.SilverType
import com.uguinformatica.bluemoon.androidapp.domain.models.Tradeable
import dagger.hilt.android.lifecycle.HiltViewModel

//@HiltViewModel
class SimulationViewModel : ViewModel() {

    private var _tradeableItemList = MutableLiveData(mutableListOf<Tradeable>())
    private var _tradeableItem = MutableLiveData(Tradeable(0.0,"",0.0, SilverType("",0.0)))
    private var _weight = MutableLiveData("")
    private var _description = MutableLiveData("")
    private var _silverTypeList = MutableLiveData(listOf<SilverType>(
        SilverType("a", 12.0)
    ))
    private var _silverType = MutableLiveData<SilverType>(null)
    private var _openAlertDialog = MutableLiveData(false)
    private var _openAddItemDialog = MutableLiveData(false)
    private var _openModifyItemDialog = MutableLiveData(false)
    private var _showSilverTypeList = MutableLiveData(false)
    private var _totalTrade = MutableLiveData(0.0)

    val weight: LiveData<String> = _weight
    val description: LiveData<String> = _description
    val silverTypeList: LiveData<List<SilverType>> = _silverTypeList
    val silverType: LiveData<SilverType> = _silverType
    val openAlertDialog:LiveData<Boolean> = _openAlertDialog
    val openAddItemDialog: LiveData<Boolean> = _openAddItemDialog
    val tradeableItemList: LiveData<MutableList<Tradeable>> = _tradeableItemList
    val tradeableItem: LiveData<Tradeable> = _tradeableItem
    val openModifyItemDialog: LiveData<Boolean> = _openModifyItemDialog
    val showSilverTypeList: LiveData<Boolean> = _showSilverTypeList
    val totalTrade: LiveData<Double> = _totalTrade

    fun setWeight(weight: String) {
        _weight.postValue(weight)
    }

    fun addTradeable(tradeable: Tradeable) {
        _tradeableItemList.value?.add(tradeable)
    }

    fun setDescription(description: String) {
        _description.postValue(description)
    }

    fun changeOpenAlertDialog(openAlertDialog: Boolean) {
        _openAlertDialog.postValue(!openAlertDialog)
    }

    fun changeOpenAddItemDialog(openAddItemDialog: Boolean) {
        _openAddItemDialog.postValue(!openAddItemDialog)
    }

    fun changeShowSilverTypeList(showSilverType: Boolean) {
        _showSilverTypeList.postValue(!showSilverType)
    }

    fun changeOpenModifyItemDialog(openModifyItemDialog: Boolean, tradeable: Tradeable) {
        _tradeableItem.value = tradeable
        setWeight(tradeable.weight.toString())
        setDescription(tradeable.description)
        _openModifyItemDialog.postValue(!openModifyItemDialog)
    }

    fun changeTradeable(tradeable: Tradeable) {
        _tradeableItem.postValue(tradeable)
    }

    fun changeSilverType(silverType: SilverType) {
        _silverType.postValue(silverType)
    }

    fun deleteTradeable(tradeable: Tradeable) {
        _tradeableItemList.value?.remove(tradeable)
    }

    fun calculateTotalTrade() {
        _totalTrade.value = 0.0
        _tradeableItemList.value?.map {
            _totalTrade.value = _totalTrade.value!! + it.sellPrice?.toDouble()!!
        }
        println(_totalTrade.value)
    }

    fun modifyTradeable(tradeable: Tradeable) {
        tradeable.weight = _weight.value?.toDouble()!!
        tradeable.description = description.value!!
        tradeable.sellPrice = _weight.value?.toFloat()!! * _silverType.value?.currentPrice!!
        tradeable.sliverType = _silverType.value!!
    }
}