package com.themoviedatabase.core.presentation.contracts


interface BasePresenterContract<T : BaseViewContract> {
    fun attach(view: T)
    fun detach()
    fun getView():T
}

class Presenter<T: BaseViewContract>: BasePresenterContract<T> {
    override fun attach(view: T) {
        TODO("Not yet implemented")
    }

    override fun detach() {
        TODO("Not yet implemented")
    }

    override fun getView(): T {
        TODO("Not yet implemented")
    }

}