package com.uguinformatica.bluemoon.androidapp.domain.models

data class CreateUser(
    var username: String,
    var name: String,
    var address: String,
    var surnames: String,
    var email: String,
    var password: String
)
