package com.themoviedatabase.android.domain.usecases.collection.movies

import com.themoviedatabase.android.di.distpacher.IoDispatcher
import com.themoviedatabase.android.domain.model.colletions.MDBCollection
import com.themoviedatabase.android.domain.model.colletions.MDBCollectionCategory
import com.themoviedatabase.android.domain.repository.collections.CollectionRepository
import com.themoviedatabase.core.domain.flow.FlowUseCase
import com.themoviedatabase.core.domain.model.MDBResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieCollectionUseCase @Inject constructor(@IoDispatcher dispatcher: CoroutineDispatcher, private val repository: CollectionRepository) : FlowUseCase<MDBCollectionCategory, List<MDBCollection>>(dispatcher) {
    override fun execute(parameters: MDBCollectionCategory): Flow<MDBResult<List<MDBCollection>>> {
        return when(parameters) {
            is MDBCollectionCategory.Latest -> {
                repository.getRecentMoviesCollection()
            }

            is MDBCollectionCategory.Popular -> {
                repository.getMoviesPopularCollection()
            }

            else -> {
                repository.getMoviesUpComingCollection()
            }
        }

    }
}