package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

//@HiltViewModel
class LoginViewModel : ViewModel() {

    private var _username = MutableLiveData("")
    private var _password = MutableLiveData("")
    private var _showPassword = MutableLiveData(false)
    private var _loginOK = MutableLiveData(false)

    val username: LiveData<String> = _username
    val password: LiveData<String> = _password
    val showPassword: LiveData<Boolean> = _showPassword
    val loginOK: LiveData<Boolean> = _loginOK

    fun changeHideNonePassword(none: VisualTransformation, hide: VisualTransformation): VisualTransformation {
        return if (_showPassword.value == true) {
            none
        } else {
            hide
        }
    }

    fun changeIcon(openEye: ImageVector, closedEye: ImageVector): ImageVector {
        return if (_showPassword.value == true) {
            openEye
        } else {
            closedEye
        }
    }

    fun checkFields() {
        if (checkUsername() && checkPassword()) {
            _loginOK.value = true
        }
    }

    private fun checkUsername(): Boolean {
        return _username.value != ""
    }

    private fun checkPassword(): Boolean {
        return _password.value != ""
    }

    fun setUsername(username: String) {
        _username.postValue(username)
    }

    fun setPassword(password: String) {
        _password.postValue(password)
    }
}