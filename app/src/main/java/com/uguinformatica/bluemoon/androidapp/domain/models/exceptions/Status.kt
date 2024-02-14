package com.uguinformatica.bluemoon.androidapp.domain.models.exceptions

sealed class Status<T> {
    data class Success<T>(val data: T): Status<T>()
    data class Error<T>(val error: ErrorType): Status<T>()
}