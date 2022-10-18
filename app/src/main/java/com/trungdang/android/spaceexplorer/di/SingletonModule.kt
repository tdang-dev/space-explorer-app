package com.trungdang.android.spaceexplorer.di

import com.trungdang.android.spaceexplorer.data.repository.body.BodyRepository
import com.trungdang.android.spaceexplorer.data.repository.body.BodyRepositoryImpl
import com.trungdang.android.spaceexplorer.data.repository.local.BodyLocalDataSource
import com.trungdang.android.spaceexplorer.data.repository.local.BodyLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    fun provideBodyLocalDataSource(defaultCoroutineDispatcher: CoroutineDispatcher): BodyLocalDataSource {
        return BodyLocalDataSourceImpl(defaultCoroutineDispatcher)
    }

    @Provides
    fun provideBodyRepository(localDataSource: BodyLocalDataSource): BodyRepository {
        return BodyRepositoryImpl(localDataSource)
    }


}