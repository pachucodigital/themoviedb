package com.themoviedatabase.android.presentation.movies.view

import com.themoviedatabase.android.ui.home.movies.model.MDBCollection
import com.themoviedatabase.core.presentation.contracts.BaseViewContract

interface MovieView: BaseViewContract {
    fun showCollectionMovies(collection: List<MDBCollection>)
}