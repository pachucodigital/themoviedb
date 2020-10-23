package com.themoviedatabase.android.domain.model.movies

sealed class MDBMoviesCategory {
    object Latest: MDBMoviesCategory()
    object Popular: MDBMoviesCategory()
    object UpComing: MDBMoviesCategory()
}