package com.uguinformatica.bluemoon.androidapp.ui.viewmodels

import android.util.Patterns
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uguinformatica.bluemoon.androidapp.domain.models.User
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    val userUseCase: UserUseCase
) : ViewModel() {

    private var _name = MutableLiveData("")
    private var _surname = MutableLiveData("")
    private var _email = MutableLiveData("")
    private var _username = MutableLiveData("")
    private var _password = MutableLiveData("")
    private var _confirmPassword = MutableLiveData("")
    private var _address = MutableLiveData("")
    private var _areFieldsEnabled = MutableLiveData(false)
    private var _emailOK = MutableLiveData(false)
    private var _showPassword = MutableLiveData(false)
    private var _arePasswordFieldsEnabled = MutableLiveData(false)

    val name: LiveData<String> = _name
    val surname: LiveData<String> = _surname
    val email: LiveData<String> = _email
    val username: LiveData<String> = _username
    val password: LiveData<String> = _password
    val confirmPassword: LiveData<String> = _confirmPassword
    val address: LiveData<String> = _address
    val areFieldsEnabled: LiveData<Boolean> = _areFieldsEnabled
    val arePasswordFieldsEnabled: LiveData<Boolean> = _arePasswordFieldsEnabled
    val showPassword: LiveData<Boolean> = _showPassword


    fun fetchUserData() {

        viewModelScope.launch {

            val userStatus = withContext(Dispatchers.IO) {
                userUseCase.getUser()
            }

            when (userStatus) {

                is Status.Success -> {
                    val user = userStatus.data
                    _username.postValue(user.userName)
                    _name.postValue(user.name)
                    _surname.postValue(user.surnames)
                    _email.postValue(user.email)
                    _address.postValue(user.address)
                }

                is Status.Error -> {

                }
            }
        }
    }

    fun haveSomeFieldChanged(user: User): Boolean {
        // TODO: Implement check if some field has changed
        return true
    }

    fun updateUser() {


        viewModelScope.launch(Dispatchers.IO) {
            if (checkFields()) {
                try {
                    val user = User(
                        userName = _username.value!!,
                        name = _name.value!!,
                        surnames = _surname.value!!,
                        email = _email.value!!,
                        address = _address.value!!,
                        balance = 0.0,
                    )

                    if (haveSomeFieldChanged(user)) {
                        userUseCase.updateUser(user)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }


        }
        disableModify()
    }

    fun updatePassword() {
        if (arePasswordEquals()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    userUseCase.updateUserPassword(_password.value!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            _password.postValue("")
            _confirmPassword.postValue("")
        }
        disableModifyPassword()
    }

    private fun disableModify() {
        _areFieldsEnabled.value = false
    }

    private fun arePasswordEquals(): Boolean {
        return _password.value == _confirmPassword.value
    }

    fun checkEmail(email: String) {
        _emailOK.value = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun checkFields(): Boolean {
        if (_name.value == "" || _surname.value == "" || _email.value == "" || _username.value == "" || _address.value == "") {
            return false
        }
        return true
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
        _areFieldsEnabled.value = true
    }

    fun enableModifyPassword() {
        _arePasswordFieldsEnabled.value = true
    }

    fun disableModifyPassword() {
        _arePasswordFieldsEnabled.value = false
    }
}