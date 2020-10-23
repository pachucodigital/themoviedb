package com.themoviedatabase.android.data.auth.requesttoken

import android.util.Log
import com.themoviedatabase.android.data.auth.requesttoken.datasource.RequestTokenApiDataSource
import com.themoviedatabase.android.data.model.auth.ValidateTokenRequestParam
import com.themoviedatabase.android.domain.model.auth.MDBRequestToken
import com.themoviedatabase.android.domain.repository.auth.RequestTokenRepository
import com.themoviedatabase.core.domain.handler.CoroutineExceptionHandler
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RequestTokenRepositoryImp @Inject constructor(private val dataSource: RequestTokenApiDataSource) : RequestTokenRepository{
    @ExperimentalCoroutinesApi
    override fun getRequestToken(): Flow<MDBResult<MDBRequestToken>> {
        return  flow {
            dataSource.requestToken().onStart {
                emit(MDBResult.Loading)
            }.map {
                MDBRequestToken(it.expires_at, it.request_token)
            }.collect {
                emit(MDBResult.Success(it))
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun validateTokenWithLogin(parameters: ValidateTokenRequestParam): Flow<MDBResult<MDBRequestToken>> {
        return  flow {
            dataSource.validateToken(parameters).catch {

                emit(MDBResult.Error(CoroutineExceptionHandler().apply(it)))
            }.onStart {
                emit(MDBResult.Loading)
            }.map {
                MDBRequestToken(it.expires_at, it.request_token)
            }.collect {
                emit(MDBResult.Success(it))
            }
        }
    }
}