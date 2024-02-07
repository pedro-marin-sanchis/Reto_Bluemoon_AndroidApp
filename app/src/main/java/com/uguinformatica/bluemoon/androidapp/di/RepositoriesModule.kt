package com.uguinformatica.bluemoon.androidapp.di

import com.uguinformatica.bluemoon.androidapp.data.repsotories.LoginRepositoryImpl
import com.uguinformatica.bluemoon.androidapp.data.repsotories.UserRepositoryImpl
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ILoginRepository
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {
    @Binds
    abstract fun bindLoginRepository(
        pokemonListRepository: LoginRepositoryImpl
    ): ILoginRepository

    @Binds
    abstract fun bindUserRepository(
        pokemonListRepository: UserRepositoryImpl
    ): IUserRepository
}