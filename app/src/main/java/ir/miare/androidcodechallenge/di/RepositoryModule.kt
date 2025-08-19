package ir.miare.androidcodechallenge.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.miare.androidcodechallenge.data.repository.FollowedPlayerRepositoryImpl
import ir.miare.androidcodechallenge.domain.repository.FollowedPlayerRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFollowedPlayerRepository(
        impl: FollowedPlayerRepositoryImpl
    ): FollowedPlayerRepository
}