package com.themoviedatabase.android.domain.repository.auth

import com.themoviedatabase.android.domain.model.auth.MDBRequestToken
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.flow.Flow

interface RequestTokenRepository {
    fun getRequestToken(): Flow<MDBResult<MDBRequestToken>>
}