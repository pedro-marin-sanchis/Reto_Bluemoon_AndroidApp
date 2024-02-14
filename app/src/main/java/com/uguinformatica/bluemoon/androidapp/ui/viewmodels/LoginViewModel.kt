package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.UserLogin
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.BadRequest
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Unauthorized
import com.uguinformatica.bluemoon.androidapp.domain.usecase.LoginUseCase
import com.uguinformatica.bluemoon.androidapp.ui.ErrorTypeToStringConverterImpl
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
    private var _showToast = MutableLiveData(false)

    val username: LiveData<String> = _username
    val password: LiveData<String> = _password

    val isLoged: LiveData<Boolean> = _isLoged
    val showErrorToast: LiveData<Boolean> = _showToast

    var toastMessage = ""

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


                val loginStatus = loginUseCase.login(loginData)

                when (loginStatus) {
                    is Status.Success -> {
                        _isLoged.postValue(true)
                        _username.postValue("")
                        _password.postValue("")
                    }
                    is Status.Error -> {
                        showToast(ErrorTypeToStringConverterImpl().convert(loginStatus.error))
                    }
                }




            }
        }
    }

    private fun showToast(message: String) {

        toastMessage = message

        _showToast.postValue(true)
    }

    fun hideToast() {
        _showToast.postValue(false)
    }

    private fun checkUsername(): Boolean {
        return _username.value != "" || _username.value != null
    }

    private fun checkPassword(): Boolean {
        return _password.value != "" || _password.value != null
    }

    fun setUsername(username: String) {
        _username.value = username
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setIsLoged(isLoged: Boolean) {
        _isLoged.postValue(isLoged)
    }
}