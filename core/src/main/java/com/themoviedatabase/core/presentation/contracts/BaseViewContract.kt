package com.themoviedatabase.core.presentation.contracts

interface BaseViewContract {
    fun initView()
    fun showMessage()
    fun showError()
    fun getPresenter(): BasePresenterContract<BaseViewContract>

}