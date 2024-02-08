package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
): ViewModel() {


    fun logout() {
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                loginUseCase.logout()
            }
        }
    }
}