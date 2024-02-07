package com.uguinformatica.bluemoon.androidapp.di

import com.uguinformatica.bluemoon.androidapp.data.repsotories.LoginRepositoryImpl
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ILoginRepository
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

}