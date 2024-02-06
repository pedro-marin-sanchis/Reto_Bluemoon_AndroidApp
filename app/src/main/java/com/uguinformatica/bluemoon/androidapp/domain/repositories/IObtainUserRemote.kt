package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.User

interface IObtainUserRemote {
    fun obtainUser(): User
}