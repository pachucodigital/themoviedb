package com.themoviedatabase.android.presentation.siging.presenter

import android.util.Log
import com.themoviedatabase.android.domain.model.auth.MDBRequestToken
import com.themoviedatabase.android.domain.usecases.auth.RequestTokenUseCase
import com.themoviedatabase.android.presentation.siging.view.SigingView
import com.themoviedatabase.core.domain.model.MDBResult
import com.themoviedatabase.core.presentation.base.MDBBasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.logging.Logger
import javax.inject.Inject

class SigingPresenter @Inject constructor(private val requestTokenUseCase: RequestTokenUseCase) : MDBBasePresenter<SigingView>() {

    private var requestToken: MDBRequestToken? = null

    @ExperimentalCoroutinesApi
    private fun requestToken() {
        launch {
            requestTokenUseCase.invoke().onCompletion {
                withContext(Dispatchers.Main){
                    view?.showLoader(false)
                }
            }.collect {
                when(it) {
                    is MDBResult.Success -> {
                        requestToken = it.data
                        Log.i("Siging", "requestToken ${requestToken?.requestToken}")
                    }

                    is MDBResult.Error -> {
                        it.exception.message?.let {
                                message -> view?.showMessage(message)
                        }
                        Log.e("Siging", "$it")
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            view?.showLoader(true)
                        }
                    }
                }
            }
        }
    }

    fun createSessionLogin(user: String, password: String) {

    }

    fun requestSessionId(token: String) {
        launch {

        }
    }

    fun doLogin(user: String, password: String) {
        requestToken()
    }


}