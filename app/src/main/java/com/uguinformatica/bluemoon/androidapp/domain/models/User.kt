package com.uguinformatica.bluemoon.androidapp.domain.models

data class User(
    var userName: String,
    var name: String,
    var surnames: String,
    var email: String,
    var address: String,
    var balance: Float,
    var password: String
)
