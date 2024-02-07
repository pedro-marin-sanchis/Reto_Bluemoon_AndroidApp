package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ForgotPasswordViewModel : ViewModel() {

    private var _password = MutableLiveData("")
    private var _confirmPassword = MutableLiveData("")
    private var _showPassword = MutableLiveData(false)
    private var _resetPassword = MutableLiveData(false)

    val password: LiveData<String> = _password
    val confirmPassword: LiveData<String> = _confirmPassword
    val showPassword: LiveData<Boolean> = _showPassword
    val resetPassword: LiveData<Boolean> = _resetPassword

    private fun arePasswordEquals(): Boolean {
        return _password.value == _confirmPassword.value
    }

    private fun checkPassword(): Boolean {
        return _password.value != ""
    }

    private fun checkConfirmPassword(): Boolean {
        return _confirmPassword.value != ""
    }

    fun checkFields() {
        if (checkPassword() && checkConfirmPassword() && arePasswordEquals()) {
            _resetPassword.value = true
        }
    }

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

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }
}