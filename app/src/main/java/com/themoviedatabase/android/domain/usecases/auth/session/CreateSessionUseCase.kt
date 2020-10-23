package com.themoviedatabase.android.domain.usecases.auth.session

import com.themoviedatabase.android.data.model.auth.CreateSessionRequestParam
import com.themoviedatabase.android.di.distpacher.IoDispatcher
import com.themoviedatabase.android.domain.repository.auth.session.SessionRepository
import com.themoviedatabase.core.domain.flow.FlowUseCase
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(@IoDispatcher dispatcher: CoroutineDispatcher, private val repository: SessionRepository): FlowUseCase<CreateSessionRequestParam, String>(dispatcher) {
    override fun execute(parameters: CreateSessionRequestParam): Flow<MDBResult<String>> {
        return repository.createSession(parameters)
    }
}