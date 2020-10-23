package com.themoviedatabase.android.domain.usecases.auth

import com.themoviedatabase.android.data.model.auth.ValidateTokenRequestParam
import com.themoviedatabase.android.di.distpacher.IoDispatcher
import com.themoviedatabase.android.domain.model.auth.MDBRequestToken
import com.themoviedatabase.android.domain.repository.auth.RequestTokenRepository
import com.themoviedatabase.core.domain.flow.FlowUseCase
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ValidateTokenUseCase @Inject constructor(@IoDispatcher dispatcher: CoroutineDispatcher, private val repository: RequestTokenRepository): FlowUseCase<ValidateTokenRequestParam, MDBRequestToken>(dispatcher) {
    override fun execute(parameters: ValidateTokenRequestParam): Flow<MDBResult<MDBRequestToken>> {
        return repository.validateTokenWithLogin(parameters)
    }
}