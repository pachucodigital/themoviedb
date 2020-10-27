package com.themoviedatabase.android.presentation.siging.presenter

import android.util.Log
import com.themoviedatabase.android.R
import com.themoviedatabase.android.data.model.auth.CreateSessionRequestParam
import com.themoviedatabase.android.data.model.auth.SigningRequestParams
import com.themoviedatabase.android.data.model.auth.ValidateTokenRequestParam
import com.themoviedatabase.android.di.distpacher.MainDispatcher
import com.themoviedatabase.android.domain.model.auth.MDBRequestToken
import com.themoviedatabase.android.domain.model.auth.exception.SigningException
import com.themoviedatabase.android.domain.usecases.auth.session.CreateSessionUseCase
import com.themoviedatabase.android.domain.usecases.auth.token.ValidateTokenUseCase
import com.themoviedatabase.android.domain.usecases.auth.token.RequestTokenUseCase
import com.themoviedatabase.android.domain.usecases.sigining.ValidateFormSigingUseCase
import com.themoviedatabase.android.presentation.siging.view.SigingView
import com.themoviedatabase.core.domain.exception.MDBException
import com.themoviedatabase.core.domain.model.MDBResult
import com.themoviedatabase.core.presentation.base.MDBBasePresenter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SigingPresenter @Inject constructor(@MainDispatcher private val mainDispatcher: CoroutineDispatcher,
                                          private val validateFormUseCase: ValidateFormSigingUseCase,
                                          private val requestTokenUseCase: RequestTokenUseCase,
                                          private val validateTokenUseCase: ValidateTokenUseCase,
                                          private val createSessionUseCase: CreateSessionUseCase,
) : MDBBasePresenter<SigingView>() {

    @ExperimentalCoroutinesApi
    private fun requestToken(params: SigningRequestParams) {
        launch {
            requestTokenUseCase.invoke().collect {
                when(it) {
                    is MDBResult.Success -> {
                        validateToken(it.data, params)
                    }
                    is MDBResult.Error -> {
                        launch(mainDispatcher) {
                            it.exception.message?.let {
                                message -> view?.showMessage(message)
                            }
                            view?.showLoader(false)
                        }
                    }
                    else -> {
                        withContext(mainDispatcher) {
                            view?.showLoader(true)
                        }
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    private fun validateToken(requestToken: MDBRequestToken, params: SigningRequestParams) {
        launch {
            validateTokenUseCase.invoke(ValidateTokenRequestParam(requestToken.requestToken, params.user, params.password)).collect {
                when(it) {
                    is MDBResult.Success -> {
                        createSessionLogin(it.data.requestToken)
                    }
                    is MDBResult.Error -> {
                        launch(mainDispatcher) {
                            when(val error =  it.exception) {
                                is MDBException -> {
                                    when(error.code) {
                                        30 -> { // Not Valid Credentials show localizable error
                                            view?.showMessage(R.string.error_code_invalid_credentials)
                                        }
                                        else -> { //unknown error show error from response
                                            view?.showMessage(error.apiError.status_message)
                                        }
                                    }
                                }
                                else -> {
                                    it.exception.message?.let {
                                        message -> view?.showMessage(message)
                                    }
                                }
                            }
                            view?.showLoader(false)
                        }
                    }
                    else -> {
                        withContext(mainDispatcher) {
                            view?.showLoader(true)
                        }
                    }
                }
            }
        }
    }


    private fun createSessionLogin(token: String) {
        launch {
            createSessionUseCase.invoke(CreateSessionRequestParam(token)).collect {
                when (it){
                    is MDBResult.Success -> {
                        launch(mainDispatcher) {
                           view?.loginSuccess()
                        }
                    }
                    is MDBResult.Error -> {
                        launch(mainDispatcher) {
                            view?.showMessage(it.exception.message!!)
                            view?.showLoader(false)
                        }
                    }
                    else -> {
                        launch(mainDispatcher) {
                            view?.showLoader(true)
                        }
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun validateFields(user: String, password: String) {
       launch {
            val params = SigningRequestParams(user, password)
            validateFormUseCase.invoke(params).collect {
                when (it){
                    is MDBResult.Success -> {
                        requestToken(params)
                    }
                    is MDBResult.Error -> {
                        Log.e("", "${it.exception}")
                        val error = it.exception
                        if(error is SigningException) {
                            launch(mainDispatcher) {
                                when (error.code) {
                                    SigningException.ALL_FIELDS_EMPTY -> {
                                        view?.showUserFieldError(R.string.obligatory_empty_field)
                                        view?.showPasswordFieldError(R.string.obligatory_empty_field)
                                    }
                                    SigningException.USER_IS_EMPTY -> {
                                        view?.showUserFieldError(R.string.obligatory_empty_field)
                                    }
                                    SigningException.PASSWORD_IS_EMPTY -> {
                                        view?.showPasswordFieldError(R.string.obligatory_empty_field)
                                    }
                                    else -> {
                                        view?.showPasswordFieldError(R.string.password_error_to_short)
                                    }
                                }
                                view?.showLoader(false)
                            }
                        }
                    }
                    else -> {
                        launch(mainDispatcher) {
                            view?.showLoader(true)
                        }
                    }
                }
            }
        }
    }


}