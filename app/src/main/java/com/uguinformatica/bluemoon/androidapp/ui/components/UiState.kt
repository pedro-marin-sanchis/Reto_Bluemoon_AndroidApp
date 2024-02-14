package com.uguinformatica.bluemoon.androidapp.ui.components

sealed class UiState<T> {
    class Loading<T> : UiState<T>()
    data class Error<T>(val error: String) : UiState<T>()
    data class Loaded<T>(val data: T) : UiState<T>()
}