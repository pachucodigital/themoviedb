package com.themoviedatabase.android.domain.usecases.auth.token

import com.themoviedatabase.android.di.distpacher.IoDispatcher
import com.themoviedatabase.android.domain.model.auth.MDBRequestToken
import com.themoviedatabase.android.domain.repository.auth.token.RequestTokenRepository
import com.themoviedatabase.core.domain.flow.SingleFlowUseCase
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RequestTokenUseCase @Inject constructor(@IoDispatcher dispatcher: CoroutineDispatcher, private val repository: RequestTokenRepository): SingleFlowUseCase<MDBRequestToken>(dispatcher) {
    override fun execute(): Flow<MDBResult<MDBRequestToken>> {
        return repository.getRequestToken()
    }
}