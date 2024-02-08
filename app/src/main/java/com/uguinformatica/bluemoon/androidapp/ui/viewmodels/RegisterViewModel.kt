package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import android.util.Patterns
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.CreateUser
import com.uguinformatica.bluemoon.androidapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val userUseCase: UserUseCase
) : ViewModel() {

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
    val isRegisterEnabled: LiveData<Boolean> = _modify
    val emailOK: LiveData<Boolean> = _emailOK
    val showPassword: LiveData<Boolean> = _showPassword

    fun register() {
        println("registering user... via viewmodel")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                println("registering user... via viewmodel in IO")
                if (!checkFields()) {
                    println("fields are not ok")
                    return@withContext
                }
                val user = CreateUser(
                    _username.value!!,
                    _name.value!!,
                    _address.value!!,
                    _surname.value!!,
                    _email.value!!,
                    _password.value!!
                )
                println("registering user: $user")

                userUseCase.registerUser(user)
            }
        }
    }

    private fun disableModify() {
        _modify.value = false
    }

    private fun arePasswordEquals(): Boolean {
        return _password.value == _confirmPassword.value
    }

    private fun checkName(): Boolean {
        println("name: ${_name.value}")
        if (_name.value == "") {
            println("name is empty")
        }
        return _name.value != ""
    }

    private fun checkSurname(): Boolean {
        if (_surname.value == "") {
            println("surname is empty")
        }

        return _surname.value != ""
    }

    private fun checkUsername(): Boolean {
        if (
            _username.value == ""
        ) {
            println("username is empty")
        }
        return _username.value != ""
    }

    private fun checkAddress(): Boolean {
        if (_address.value == "") {
            println("address is empty")
        }
        return _address.value != ""
    }


    fun checkEmail(): Boolean {
        if (_email.value == "" || _email.value == null) {
            return false
        }

        return Patterns.EMAIL_ADDRESS.matcher(_email.value!!).matches()
    }

    fun checkFields(): Boolean {
        return checkName() && checkSurname() && checkUsername() &&
                checkEmail() && arePasswordEquals() && checkAddress()
    }

    fun changeHideNonePassword(
        none: VisualTransformation,
        hide: VisualTransformation
    ): VisualTransformation {
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
        _name.postValue(name)
    }

    fun setSurname(surname: String) {
        _surname.postValue(surname)
    }

    fun setEmail(email: String) {
        _email.postValue(email)
    }

    fun setUsername(username: String) {
        _username.postValue(username)
    }

    fun setPassword(password: String) {
        _password.postValue(password)
    }

    fun setConfirmPassword(confirmPassword: String) {
        _confirmPassword.postValue(confirmPassword)
    }

    fun setAddress(address: String) {
        _address.postValue(address)
    }

    fun enableRegister() {
        _modify.postValue(true)
    }
}