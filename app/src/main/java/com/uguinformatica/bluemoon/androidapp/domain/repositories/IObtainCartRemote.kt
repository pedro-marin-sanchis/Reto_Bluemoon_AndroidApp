package com.uguinformatica.bluemoon.androidapp.domain.repositories

import com.uguinformatica.bluemoon.androidapp.domain.models.CartItem

interface IObtainCartRemote {
    fun obtainCartItemsList(): List<CartItem>
}