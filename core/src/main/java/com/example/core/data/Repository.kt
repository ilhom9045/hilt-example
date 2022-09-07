package com.example.core.data

import com.example.core.data.model.ResponseDTOModel

interface Repository {

    suspend fun news(): ResponseDTOModel

    class Base(
        private val localDataRepository: LocalDataRepository,
        private val api: Api
    ) : Repository {
        override suspend fun news(): ResponseDTOModel {
            return localDataRepository.readResponse() ?: api.topHeadlines().body()!!
                .apply { localDataRepository.saveResponse(this) }
        }

    }

}