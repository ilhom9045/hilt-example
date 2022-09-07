package com.example.daggerhiltinmultymoduleproject.di

import android.content.Context
import com.example.core.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(): RemoteRepository {
        return RemoteRepository.Base()
    }

    @Provides
    @Singleton
    fun provideApi(remoteRepository: RemoteRepository): Api {
        return remoteRepository.createTService()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(@ApplicationContext context: Context): LocalDataRepository {
        return LocalDataRepository.Base(context)
    }
}