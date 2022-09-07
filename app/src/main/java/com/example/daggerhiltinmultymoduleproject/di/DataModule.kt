package com.example.daggerhiltinmultymoduleproject.di

import com.example.core.data.Api
import com.example.core.data.LocalDataRepository
import com.example.core.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    fun provideRepository(localDataRepository: LocalDataRepository, api: Api): Repository {
        return Repository.Base(localDataRepository, api)
    }

}