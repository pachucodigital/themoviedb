package com.themoviedatabase.android.domain.repository.auth.session

import com.themoviedatabase.android.data.model.auth.CreateSessionRequestParam
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun createSession(params: CreateSessionRequestParam): Flow<MDBResult<String>>
}