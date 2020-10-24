package com.themoviedatabase.android.domain.model.colletions

sealed class MDBCollectionCategory {
    object Latest: MDBCollectionCategory() {
        override fun toString(): String {
            return "1"
        }
    }
    object Popular: MDBCollectionCategory(){
        override fun toString(): String {
            return "2"
        }
    }
    object UpComing: MDBCollectionCategory(){
        override fun toString(): String {
            return "3"
        }
    }
}