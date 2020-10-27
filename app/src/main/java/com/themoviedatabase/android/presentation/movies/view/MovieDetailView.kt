package com.themoviedatabase.android.presentation.movies.view

import com.themoviedatabase.android.domain.model.movies.MDBMovie
import com.themoviedatabase.core.presentation.contracts.BaseViewContract

interface MovieDetailView: BaseViewContract {
    fun showDetailMovie(movie: MDBMovie)
}