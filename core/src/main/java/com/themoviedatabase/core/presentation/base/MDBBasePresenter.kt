package com.themoviedatabase.core.presentation.base

import com.themoviedatabase.core.presentation.contracts.BasePresenterContract
import com.themoviedatabase.core.presentation.contracts.BaseViewContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class MDBBasePresenter<T: BaseViewContract> : BasePresenterContract<T>, CoroutineScope {
    protected var view: T? = null
    val job:Job = Job()
    override val coroutineContext: CoroutineContext
        get() = job


    override fun attach(view: T) {
        this.view = view
    }

    override fun detach() {
        this.view = null
        job.cancel()
    }

}