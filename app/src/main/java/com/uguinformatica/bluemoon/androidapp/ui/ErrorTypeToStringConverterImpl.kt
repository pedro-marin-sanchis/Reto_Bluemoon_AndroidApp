package com.uguinformatica.bluemoon.androidapp.ui

import android.app.AlertDialog
import android.content.Context
import androidx.compose.runtime.Composable
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.ErrorType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface ErrorTypeConverter<T> {

    fun convert(errorType: ErrorType): T
}

class ErrorTypeToStringConverterImpl : ErrorTypeConverter<String> {

    override fun convert(errorType: ErrorType) = when (errorType) {
        ErrorType.Api.NotFound -> "Resource not found"
        ErrorType.Api.BadRequest -> "Bad request"
        ErrorType.Api.Network -> "Check your internet connection"
        ErrorType.Api.Unauthorized -> "Your session has expired, please log in again"
        else -> {
            "An error occurred"
        }
    }
}


class ErrorTypeToComposablePopupConverterImpl  @Inject constructor(
    @ApplicationContext val context: Context
): ErrorTypeConverter<@Composable ()->Unit>{
    override fun convert(errorType: ErrorType): @Composable () -> Unit = when (errorType){
        ErrorType.Api.NotFound -> {
            {
                AlertDialog.Builder(context)
                    .setTitle("Resource not found")
                    .setMessage("The resource you are trying to access was not found")
                    .setPositiveButton("Ok", null)
                    .show()
            }
        }

        else -> {
            {
                AlertDialog.Builder(context)
                    .setTitle("An error occurred")
                    .setMessage("An error occurred")
                    .setPositiveButton("Ok", null)
                    .show()
            }
        }
    }

}
