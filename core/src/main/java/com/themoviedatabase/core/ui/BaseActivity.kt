package com.themoviedatabase.core.ui

import androidx.appcompat.app.AppCompatActivity
import com.themoviedatabase.core.presentation.contracts.BasePresenterContract
import com.themoviedatabase.core.presentation.contracts.BaseViewContract

abstract class BaseActivity<P: BasePresenterContract<out BaseViewContract>>: AppCompatActivity(){

    lateinit var presenterContract: P

}