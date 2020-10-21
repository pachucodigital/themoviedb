package com.themoviedatabase.core.presentation.contracts

import androidx.annotation.IdRes

interface BaseViewContract {
    fun initView()
    fun showMessage()
    fun showError()
    fun getPresenter(): BasePresenterContract<BaseViewContract>

    @IdRes
    fun getLayoutView():Int
}