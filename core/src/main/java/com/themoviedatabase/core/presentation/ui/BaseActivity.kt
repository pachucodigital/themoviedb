package com.themoviedatabase.core.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import com.themoviedatabase.core.presentation.contracts.BasePresenterContract
import com.themoviedatabase.core.presentation.contracts.BaseViewContract

abstract class BaseActivity<P: BasePresenterContract<BaseViewContract>>: AppCompatActivity(),  BaseViewContract{

    lateinit var presenterContract: BasePresenterContract<BaseViewContract>

    override fun showMessage() {

    }

    override fun showError() {

    }

    override fun getPresenter(): BasePresenterContract<BaseViewContract>  = presenterContract

}