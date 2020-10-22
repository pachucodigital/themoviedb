package com.themoviedatabase.core.domain.model

sealed class MDBResult<out R> {
    data class Success<out T>(val data: T) : MDBResult<T>()
    data class Error(val exception: Throwable) : MDBResult<Nothing>()
    object Loading : MDBResult<Nothing>()
}