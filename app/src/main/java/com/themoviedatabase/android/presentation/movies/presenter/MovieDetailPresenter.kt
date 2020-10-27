package com.themoviedatabase.android.presentation.movies.presenter

import android.util.Log
import com.themoviedatabase.android.di.distpacher.MainDispatcher
import com.themoviedatabase.android.domain.usecases.movies.GetDetailMovieUseCase
import com.themoviedatabase.android.presentation.movies.view.MovieDetailView
import com.themoviedatabase.core.domain.model.MDBResult
import com.themoviedatabase.core.presentation.base.MDBBasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(@MainDispatcher private val mainDispatcher: CoroutineDispatcher,
                                               private val movieDetailMovieUseCase: GetDetailMovieUseCase
                                         ) : MDBBasePresenter<MovieDetailView>() {
    @ExperimentalCoroutinesApi
    fun loadDetailMovie(id: Int) {
        launch {
            movieDetailMovieUseCase.invoke(id).onCompletion {
                launch(mainDispatcher) {
                    view?.showLoader(false)
                }
            }.collect {
                when(it) {
                    is MDBResult.Success -> {
                        launch(mainDispatcher) {
                           Log.i("getMovieCollection", "$it")
                           view?.showDetailMovie(it.data)
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