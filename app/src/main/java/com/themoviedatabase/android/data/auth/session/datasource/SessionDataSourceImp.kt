package com.themoviedatabase.android.data.auth.session.datasource

import com.themoviedatabase.android.BuildConfig
import com.themoviedatabase.android.data.api.auth.SessionApiService
import com.themoviedatabase.android.data.model.auth.CreateSessionRequestParam
import com.themoviedatabase.android.data.model.auth.SessionDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SessionDataSourceImp @Inject constructor(private val apiService: SessionApiService): SessionDataSource {
    override fun createSession(param: CreateSessionRequestParam): Flow<SessionDto> {
        return flow {
            val result = apiService.createSession(BuildConfig.API_KEY, param)
            emit(result)
        }
    }
}