package com.themoviedatabase.core.ui

import androidx.fragment.app.Fragment
import com.themoviedatabase.core.presentation.contracts.BasePresenterContract
import com.themoviedatabase.core.presentation.contracts.BaseViewContract

abstract class BaseFragment<P: BasePresenterContract<BaseViewContract>>: Fragment(), BaseViewContract {

    lateinit var presenterContract: BasePresenterContract<BaseViewContract>

}