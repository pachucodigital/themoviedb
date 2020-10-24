package com.themoviedatabase.core.domain.flow

import com.themoviedatabase.core.domain.handler.CoroutineExceptionHandler
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    operator fun invoke(parameters: P): Flow<MDBResult<R>> = execute(parameters)
        .catch { cause ->
            emit(MDBResult.Error(CoroutineExceptionHandler().apply(cause)))
        }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<MDBResult<R>>
}