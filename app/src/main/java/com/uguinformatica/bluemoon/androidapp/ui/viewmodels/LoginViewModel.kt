package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.UserLogin
import com.uguinformatica.bluemoon.androidapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
) : ViewModel() {

    private var _username = MutableLiveData("")
    private var _password = MutableLiveData("")
    private var _isLoged = MutableLiveData(false)

    val username: LiveData<String> = _username
    val password: LiveData<String> = _password
    val isLoged: LiveData<Boolean> = _isLoged

    private fun checkFields(): Boolean {
        return checkUsername() && checkPassword()
    }

    fun checkAndSetIfLoged(): Boolean {
        var result = false
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                result = loginUseCase.isLogged()
                _isLoged.postValue(result)
            }
        }
        return result
    }

    fun login() {

        if (!checkFields()) {
            return
        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val loginData = UserLogin(_username.value!!, _password.value!!)

                try {
                    loginUseCase.login(loginData)
                    _isLoged.postValue(true)

                    _username.postValue("")
                    _password.postValue("")

                } catch (e: Exception) {
                    _isLoged.postValue(false)
                }
            }
        }
    }

    private fun checkUsername(): Boolean {
        return _username.value != "" || _username.value != null
    }

    private fun checkPassword(): Boolean {
        return _password.value != "" || _password.value != null
    }

    fun setUsername(username: String) {
        _username.postValue(username)
    }

    fun setPassword(password: String) {
        _password.postValue(password)
    }

    fun setIsLoged(isLoged: Boolean) {
        _isLoged.postValue(isLoged)
    }
}