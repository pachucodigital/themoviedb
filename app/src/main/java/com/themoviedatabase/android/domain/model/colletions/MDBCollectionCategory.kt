package com.themoviedatabase.android.domain.model.colletions

sealed class MDBCollectionCategory {
    object Latest: MDBCollectionCategory()
    object Popular: MDBCollectionCategory()
    object UpComing: MDBCollectionCategory()
}