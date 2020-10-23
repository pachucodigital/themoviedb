package com.themoviedatabase.android.data.auth.requesttoken.datasource

import com.themoviedatabase.android.BuildConfig
import com.themoviedatabase.android.data.api.auth.RequestTokenApi
import com.themoviedatabase.android.data.model.auth.RequestTokenDto
import com.themoviedatabase.android.data.model.auth.ValidateTokenRequestParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RequestTokenApiDataSourceImp @Inject constructor(private val apiService: RequestTokenApi) : RequestTokenApiDataSource {
    override suspend fun requestToken(): Flow<RequestTokenDto> {
        return flow {
            val result = apiService.requestToken(BuildConfig.API_KEY)
            emit(result)
        }
    }

    override suspend fun validateToken(validateTokenRequestParam: ValidateTokenRequestParam): Flow<RequestTokenDto> {
        return flow {
            val result = apiService.validateToken(BuildConfig.API_KEY, validateTokenRequestParam)
            emit(result)
        }
    }
}