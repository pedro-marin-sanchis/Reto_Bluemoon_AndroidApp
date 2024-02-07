package com.uguinformatica.bluemoon.androidapp.domain.usecase

import com.uguinformatica.bluemoon.androidapp.domain.models.User
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IUserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    val userRepository: IUserRepository
) {
    suspend fun getUser(): User = userRepository.getUser()

    suspend fun updateUser(user: User) = userRepository.updateUser(user)

}
