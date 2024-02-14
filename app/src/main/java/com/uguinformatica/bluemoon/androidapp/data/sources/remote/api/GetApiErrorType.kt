package com.uguinformatica.bluemoon.androidapp.data.sources.remote.api

import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.ErrorType

fun getApiErrorType(responseCode: Int, body:String): ErrorType {
    return when (responseCode) {
        400 -> ErrorType.Api.BadRequest
        403 -> ErrorType.Api.Unauthorized
        404 -> ErrorType.Api.NotFound
        else -> ErrorType.Unknown
    }
}