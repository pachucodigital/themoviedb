package com.themoviedatabase.android.domain.usecases.movies

import com.themoviedatabase.android.di.distpacher.IoDispatcher
import com.themoviedatabase.android.domain.model.movies.MDBMovie
import com.themoviedatabase.android.domain.repository.movies.MovieRepository
import com.themoviedatabase.core.domain.flow.FlowUseCase
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(@IoDispatcher dispatcher: CoroutineDispatcher, private val repository: MovieRepository): FlowUseCase<Int, MDBMovie>(dispatcher) {
    override fun execute(parameters: Int): Flow<MDBResult<MDBMovie>> {
        return repository.getDetailMovie(parameters)
    }
}