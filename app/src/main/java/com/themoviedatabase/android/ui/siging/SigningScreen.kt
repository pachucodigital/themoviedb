package com.themoviedatabase.android.ui.siging

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import com.themoviedatabase.android.ui.home.MainActivity
import com.themoviedatabase.android.databinding.ScreenSigingBinding
import com.themoviedatabase.android.presentation.siging.presenter.SigingPresenter
import com.themoviedatabase.android.presentation.siging.view.SigingView
import com.themoviedatabase.core.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class SigningScreen : BaseActivity<SigingPresenter>(), SigingView {
    private lateinit var binding: ScreenSigingBinding
    @Inject
    lateinit var presenter: SigingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScreenSigingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
        binding.sigingDescription.movementMethod = LinkMovementMethod.getInstance()
        binding.btnContinue.setOnClickListener {
            validateFields()
        }
        binding.sigingInputPassword.doOnTextChanged { _, _, _, _ ->
            binding.sigingInputLayoutPassword.error = null
        }

        binding.sigingInputUser.doOnTextChanged { _, _, _, _ ->
            binding.sigingInputLayoutUser.error = null
        }
    }

    @ExperimentalCoroutinesApi
    private fun validateFields() {
        presenter.validateFields(binding.sigingInputUser.text.toString(), binding.sigingInputPassword.text.toString())
    }

    override fun loginSuccess() {
        startActivity(MainActivity.provideNavigateIntent())
    }

    override fun showUserFieldError(obligatoryEmptyField: Int) {
        binding.sigingInputLayoutUser.error = getString(obligatoryEmptyField)
    }

    override fun showPasswordFieldError(obligatoryEmptyField: Int) {
        binding.sigingInputLayoutPassword.error = getString(obligatoryEmptyField)
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
        Snackbar.make(binding.signingMainContent, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showMessage(message: Int) {
        Snackbar.make(binding.signingMainContent, message, Snackbar.LENGTH_SHORT).show()
    }


}