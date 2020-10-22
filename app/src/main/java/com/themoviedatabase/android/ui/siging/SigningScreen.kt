package com.themoviedatabase.android.ui.siging

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import com.themoviedatabase.android.data.api.auth.RequestTokenApi
import com.themoviedatabase.android.data.auth.requesttoken.RequestTokenRepositoryImp
import com.themoviedatabase.android.data.auth.requesttoken.datasource.RequestTokenApiDataSourceImp
import com.themoviedatabase.android.databinding.ScreenLoginBinding
import com.themoviedatabase.android.di.clients.RetrofitModule
import com.themoviedatabase.android.domain.usecases.auth.RequestTokenUseCase
import com.themoviedatabase.android.presentation.siging.presenter.SigingPresenter
import com.themoviedatabase.android.presentation.siging.view.SigingView
import com.themoviedatabase.core.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@AndroidEntryPoint
class SigningScreen : BaseActivity<SigingPresenter>(), SigingView {
    private lateinit var binding: ScreenLoginBinding
    @Inject
    lateinit var presenter:SigingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScreenLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
        binding.sigingDescription.movementMethod = LinkMovementMethod.getInstance()
        binding.btnContinue.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        presenter.doLogin(binding.sigingInputUser.text.toString(), binding.sigingInputPassword.text.toString())
    }

    override fun loginSuccess() {
    }

    override fun initView() {
       presenter.attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun showLoader(show: Boolean) {
        binding.loader.loadingView.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {

    }


}