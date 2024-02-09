package com.uguinformatica.bluemoon.androidapp.di

import com.uguinformatica.bluemoon.androidapp.data.repsotories.CartRepositoryImpl
import com.uguinformatica.bluemoon.androidapp.data.repsotories.LoginRepositoryImpl
import com.uguinformatica.bluemoon.androidapp.data.repsotories.OrderRepositoryImpl
import com.uguinformatica.bluemoon.androidapp.data.repsotories.ProductRepositoryImpl
import com.uguinformatica.bluemoon.androidapp.data.repsotories.TradesRepositoryImpl
import com.uguinformatica.bluemoon.androidapp.data.repsotories.UserRepositoryImpl
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ICartRepository
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ILoginRepository
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IOrdersRepository
import com.uguinformatica.bluemoon.androidapp.domain.repositories.IProductsRepository
import com.uguinformatica.bluemoon.androidapp.domain.repositories.ITradesRepository
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

    @Binds
    abstract fun bindCartRepository(
        cartRepository: CartRepositoryImpl
    ): ICartRepository

    @Binds
    abstract fun bindOrderRepository(
        cartRepository: OrderRepositoryImpl
    ): IOrdersRepository

    @Binds
    abstract fun bindProductRepository(
        cartRepository: ProductRepositoryImpl
    ): IProductsRepository

    @Binds
    abstract fun bindTradesRepository(
        cartRepository: TradesRepositoryImpl
    ): ITradesRepository
}