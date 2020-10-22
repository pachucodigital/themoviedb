package com.themoviedatabase.core.domain.flow

import com.themoviedatabase.core.domain.handler.CoroutineExceptionHandler
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    var customHandlerError: ((throwable: Throwable) -> Unit)?  = null
    operator fun invoke(parameters: P): Flow<MDBResult<R>> = execute(parameters)
        .catch { cause ->
            if(customHandlerError != null) {
                customHandlerError!!(cause)
            } else {
                emit(MDBResult.Error(CoroutineExceptionHandler().apply(cause)))
            }
        }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<MDBResult<R>>
}