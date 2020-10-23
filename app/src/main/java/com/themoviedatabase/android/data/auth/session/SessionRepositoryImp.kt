package com.themoviedatabase.android.data.auth.session

import com.themoviedatabase.android.data.auth.session.datasource.SessionDataSource
import com.themoviedatabase.android.data.model.auth.CreateSessionRequestParam
import com.themoviedatabase.android.domain.repository.auth.session.SessionRepository
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SessionRepositoryImp @Inject constructor(private val dataSource: SessionDataSource): SessionRepository {
    @ExperimentalCoroutinesApi
    override fun createSession(params: CreateSessionRequestParam): Flow<MDBResult<String>> {
        return flow {
            dataSource.createSession(params).onStart {
                emit(MDBResult.Loading)
            }.map {
                it.session_id
            }.collect {
                emit(MDBResult.Success(it))
            }
        }
    }
}