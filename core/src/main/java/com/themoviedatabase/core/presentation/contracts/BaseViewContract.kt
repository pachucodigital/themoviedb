package com.themoviedatabase.core.presentation.contracts

interface BaseViewContract {
    fun initView()
    fun showLoader(show: Boolean)
    fun showMessage(message: String)
    fun showMessage(message: Int)
}