package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.usecase.LoginUseCase
import com.uguinformatica.bluemoon.androidapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    val userUseCase: UserUseCase
) : ViewModel() {

    private var _userBalance = MutableLiveData<Double>()

    val userBalance = _userBalance


    fun fetchBalance() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val status = userUseCase.getBalance()

                when (status) {
                    is com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status.Success -> {
                        _userBalance.postValue(status.data!!)
                    }

                    is com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status.Error -> {
                        _userBalance.postValue(0.0)
                    }

                }
            }
        }
        
    }

    fun logout() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loginUseCase.logout()
            }
        }
    }
}