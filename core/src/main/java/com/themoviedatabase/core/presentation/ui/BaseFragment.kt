package com.themoviedatabase.core.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.themoviedatabase.core.presentation.contracts.BasePresenterContract
import com.themoviedatabase.core.presentation.contracts.BaseViewContract

abstract class BaseFragment<P: BasePresenterContract<BaseViewContract>>: Fragment(), BaseViewContract {

    lateinit var presenterContract: BasePresenterContract<BaseViewContract>

    override fun showMessage() {

    }

    override fun showError() {

    }

    override fun getPresenter(): BasePresenterContract<BaseViewContract> = presenterContract

}