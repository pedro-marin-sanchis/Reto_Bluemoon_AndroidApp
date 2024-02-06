package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import android.util.Patterns
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserDataViewModel : ViewModel() {

    private var _name = MutableLiveData("")
    private var _surname = MutableLiveData("")
    private var _email = MutableLiveData("")
    private var _username = MutableLiveData("")
    private var _password = MutableLiveData("")
    private var _confirmPassword = MutableLiveData("")
    private var _address = MutableLiveData("")
    private var _modify = MutableLiveData(false)
    private var _emailOK = MutableLiveData(false)
    private var _showPassword = MutableLiveData(false)

    val name: LiveData<String> = _name
    val surname: LiveData<String> = _surname
    val email: LiveData<String> = _email
    val username: LiveData<String> = _username
    val password: LiveData<String> = _password
    val confirmPassword: LiveData<String> = _confirmPassword
    val address: LiveData<String> = _address
    val modify: LiveData<Boolean> = _modify

    private fun disableModify() {
        _modify.value = false
    }

    private fun checkPassword(): Boolean {
        return _password.value == _confirmPassword.value
    }

    fun checkEmail(email: String) {
        _emailOK.value = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun checkFields() {
        if (_name.value != "" && _surname.value != "" &&
            _username.value != "" && _emailOK.value == true &&
            checkPassword() && _address.value != "")
        {
            disableModify()
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

    fun setName(name: String) {
        _name.value = name
    }

    fun setSurname(surname: String) {
        _surname.value = surname
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

    fun setAddress(address: String) {
        _address.value = address
    }

    fun enableModify() {
        _modify.value = true
    }
}