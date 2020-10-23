package com.themoviedatabase.android.ui.home.movies

import android.os.Bundle
import android.view.View
import com.themoviedatabase.android.ui.home.movies.model.MDBCollection
import com.themoviedatabase.android.domain.model.movies.MDBMoviesCategory


class LatestMoviesView : BaseMovieView() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCollection(MDBMoviesCategory.Latest)
    }
}