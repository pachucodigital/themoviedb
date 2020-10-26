package com.themoviedatabase.android.presentation.collections.presenter

import com.themoviedatabase.android.di.distpacher.IoDispatcher
import com.themoviedatabase.android.di.distpacher.MainDispatcher
import com.themoviedatabase.android.domain.model.colletions.MDBCollectionCategory
import com.themoviedatabase.android.domain.usecases.collection.movies.GetMovieCollectionUseCase
import com.themoviedatabase.android.presentation.collections.view.CollectionView
import com.themoviedatabase.android.ui.collections.model.MDBItemCollection
import com.themoviedatabase.core.domain.exception.NetworkException
import com.themoviedatabase.core.domain.model.MDBResult
import com.themoviedatabase.core.presentation.base.MDBBasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

class CollectionPresenter @Inject constructor(@MainDispatcher private val mainDispatcher: CoroutineDispatcher,
                                              @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
                                              private val movieCollectionUseCase: GetMovieCollectionUseCase
                                         ) : MDBBasePresenter<CollectionView>() {
    lateinit var resultCollection: List<MDBItemCollection>

    @ExperimentalCoroutinesApi
    fun loadCollection(category: MDBCollectionCategory) {
        launch(ioDispatcher) {
            movieCollectionUseCase.invoke(category).collect {
                when(it) {
                    is MDBResult.Success -> {
                        launch(mainDispatcher) {
                            resultCollection = it.data.map { recent->
                                MDBItemCollection(recent.id, recent.title, recent.overview, recent.poster_path, recent.toString())
                            }

                            view?.showCollectionMovies(resultCollection)
                            view?.showLoader(false)

                        }
                    }
                    is MDBResult.Error -> {
                        launch(mainDispatcher) {
                            if(it.exception is NetworkException  || it.exception is UnknownHostException) {
                                view?.showRetry()
                            } else {
                                view?.showMessage(it.exception.message!!)
                            }
                            view?.showLoader(false)
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