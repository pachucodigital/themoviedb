package com.themoviedatabase.android.domain.usecases.collection.movies

import com.themoviedatabase.android.di.distpacher.IoDispatcher
import com.themoviedatabase.android.domain.model.movies.MDBMovieCollection
import com.themoviedatabase.android.domain.model.movies.MDBMoviesCategory
import com.themoviedatabase.android.domain.repository.auth.repository.MoviesCollectionRepository
import com.themoviedatabase.core.domain.flow.FlowUseCase
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieCollectionUseCase @Inject constructor(@IoDispatcher dispatcher: CoroutineDispatcher, private val repository: MoviesCollectionRepository) : FlowUseCase<MDBMoviesCategory, List<MDBMovieCollection>>(dispatcher) {
    override fun execute(parameters: MDBMoviesCategory): Flow<MDBResult<List<MDBMovieCollection>>> {
        return when(parameters) {
            is MDBMoviesCategory.Latest -> {
                repository.getRecentMoviesCollection()
            }

            is MDBMoviesCategory.Popular -> {
                repository.getMoviesPopularCollection()

            }

            else -> {
                repository.getMoviesUpComingCollection()
            }
        }

    }
}