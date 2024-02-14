package com.uguinformatica.bluemoon.androidapp.domain.usecase

import com.uguinformatica.bluemoon.androidapp.domain.models.CreateUser
import com.uguinformatica.bluemoon.androidapp.domain.models.User
import com.uguinformatica.bluemoon.androidapp.domain.models.exceptions.Status
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IUserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    val userRepository: IUserRepository
) {
    suspend fun getUser(): Status<User> = userRepository.getUser()

    suspend fun updateUser(user: User): Status<Unit> = userRepository.updateUser(user)

    suspend fun updateUserPassword(password: String): Status<Unit> =
        userRepository.updateUserPassword(password)

    suspend fun registerUser(user: CreateUser): Status<Unit> = userRepository.createUser(user)

    suspend fun getBalance(): Status<Double> = userRepository.getBalance()
}
