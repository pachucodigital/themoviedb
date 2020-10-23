package com.themoviedatabase.android.data.auth.requesttoken.datasource

import com.themoviedatabase.android.data.model.auth.RequestTokenDto
import com.themoviedatabase.android.data.model.auth.ValidateTokenRequestParam
import kotlinx.coroutines.flow.Flow

interface RequestTokenApiDataSource {
    suspend fun requestToken(): Flow<RequestTokenDto>
    suspend fun validateToken(validateTokenRequestParam: ValidateTokenRequestParam): Flow<RequestTokenDto>
}
