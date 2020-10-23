package com.themoviedatabase.core.presentation.contracts


interface BasePresenterContract<T : BaseViewContract> {
    fun attach(view: T)
    fun detach()
}

