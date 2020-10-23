package com.themoviedatabase.android.presentation.movies.presenter

import android.util.Log
import com.themoviedatabase.android.di.distpacher.MainDispatcher
import com.themoviedatabase.android.domain.model.movies.MDBMoviesCategory
import com.themoviedatabase.android.domain.usecases.collection.movies.GetMovieCollectionUseCase
import com.themoviedatabase.android.presentation.movies.view.MovieView
import com.themoviedatabase.android.ui.home.movies.model.MDBCollection
import com.themoviedatabase.core.domain.model.MDBResult
import com.themoviedatabase.core.presentation.base.MDBBasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviePresenter @Inject constructor(@MainDispatcher private val mainDispatcher: CoroutineDispatcher,
                                         private val movieCollectionUseCase: GetMovieCollectionUseCase
                                         ) : MDBBasePresenter<MovieView>() {
    @ExperimentalCoroutinesApi
    fun loadCollection(category: MDBMoviesCategory) {
        launch {
            movieCollectionUseCase.invoke(category).onCompletion {
                launch(mainDispatcher) {
                    view?.showLoader(false)
                }
            }.collect {
                when(it) {
                    is MDBResult.Success -> {
                        launch(mainDispatcher) {
                            val collection = it.data.map { recent->
                                MDBCollection(recent.id, recent.title, recent.overview, recent.poster_path, recent.toString())
                            }
                            view?.showCollectionMovies(collection)
                        }
                    }
                    is MDBResult.Error -> {
                        Log.e("getMovieCollection", "$it")
                        launch(mainDispatcher) {
                            view?.showMessage(it.exception.message!!)
                        }
                    }
                    else -> {
                        launch(mainDispatcher) {
                            view?.showLoader(true)
                        }
                    }
                }
            }
        }

    }

}