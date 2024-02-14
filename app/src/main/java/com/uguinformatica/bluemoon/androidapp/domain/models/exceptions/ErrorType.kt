package com.uguinformatica.bluemoon.androidapp.domain.models.exceptions

sealed class ErrorType {
    sealed class Api: ErrorType() {

        object Network: Api()

        object BadRequest : Api()

        object NotFound : Api()

        object Unauthorized : Api()

        object Server : Api()
    }



    object Unknown: ErrorType()

}