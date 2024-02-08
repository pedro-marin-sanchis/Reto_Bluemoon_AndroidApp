package com.uguinformatica.bluemoon.androidapp.domain.usecase

import com.uguinformatica.bluemoon.androidapp.domain.models.CreateUser
import com.uguinformatica.bluemoon.androidapp.domain.models.User
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IUserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    val userRepository: IUserRepository
) {
    suspend fun getUser(): User = userRepository.getUser()

    suspend fun updateUser(user: User) = userRepository.updateUser(user)

    suspend fun updateUserPassword(password: String) = userRepository.updateUserPassword(password)

    suspend fun registerUser(user: CreateUser) = userRepository.createUser(user)

}
