package com.themoviedatabase.android.data.auth.session.datasource

import com.themoviedatabase.android.data.model.auth.CreateSessionRequestParam
import com.themoviedatabase.android.data.model.auth.SessionDto
import kotlinx.coroutines.flow.Flow

interface SessionDataSource  {
    fun createSession(param: CreateSessionRequestParam): Flow<SessionDto>
}