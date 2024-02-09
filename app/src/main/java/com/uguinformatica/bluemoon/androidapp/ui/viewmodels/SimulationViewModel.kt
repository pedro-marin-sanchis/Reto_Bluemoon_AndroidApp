package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.SilverType
import com.uguinformatica.bluemoon.androidapp.domain.models.Trade
import com.uguinformatica.bluemoon.androidapp.domain.models.TradeCreate
import com.uguinformatica.bluemoon.androidapp.domain.models.Tradeable
import com.uguinformatica.bluemoon.androidapp.domain.usecase.TradeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SimulationViewModel @Inject constructor(
    val tradeUseCase: TradeUseCase,
) : ViewModel() {

    private var _tradeableItemList = MutableLiveData(mutableListOf<Tradeable>())
    private var _tradeableItem = MutableLiveData<Tradeable>(null)
    private var _weight = MutableLiveData("")
    private var _description = MutableLiveData("")
    private var _sellPrice = MutableLiveData("")
    private var _silverTypeList = MutableLiveData(listOf<SilverType>())
    private var _silverType = MutableLiveData<SilverType>(null)
    private var _openAlertDialog = MutableLiveData(false)
    private var _openAddItemDialog = MutableLiveData(false)
    private var _openModifyItemDialog = MutableLiveData(false)
    private var _showSilverTypeList = MutableLiveData(false)

    val weight: LiveData<String> = _weight
    val description: LiveData<String> = _description
    val sellPrice: LiveData<String> = _sellPrice
    val silverTypeList: LiveData<List<SilverType>> = _silverTypeList
    val silverType: LiveData<SilverType> = _silverType
    val openAlertDialog:LiveData<Boolean> = _openAlertDialog
    val openAddItemDialog: LiveData<Boolean> = _openAddItemDialog
    val tradeableItemList: LiveData<MutableList<Tradeable>> = _tradeableItemList
    val tradeableItem: LiveData<Tradeable> = _tradeableItem
    val openModifyItemDialog: LiveData<Boolean> = _openModifyItemDialog
    val showSilverTypeList: LiveData<Boolean> = _showSilverTypeList


    fun fetchSilverTypes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val silverTypes = tradeUseCase.getSilverTypes()
                _silverTypeList.postValue(silverTypes)
            }
        }
    }

    fun crateTrade(){

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val tradeable = Tradeable(
                    weight = _weight.value?.toDouble()!!,
                    description = _description.value!!,
                    sliverType = _silverType.value!!
                )

                val trade = TradeCreate(
                    tradeables = mutableListOf(tradeable)
                )


                tradeUseCase.createTrade(trade)
            }
        }

    }

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

    fun setSilverType(silverType: SilverType) {
        _tradeableItem.value?.sliverType = silverType
    }

    fun changeSilverType(silverType: SilverType) {
        _silverType.postValue(silverType)
    }

    fun deleteTradeable(tradeable: Tradeable) {
        _tradeableItemList.value?.remove(tradeable)
    }

    fun modifyTradeable(tradeable: Tradeable) {
        tradeable.weight = _weight.value?.toDouble()!!
        tradeable.description = description.value!!
    }
}