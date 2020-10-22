package com.themoviedatabase.android

import android.os.Bundle
import android.os.PersistableBundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.themoviedatabase.android.databinding.ScreenLoginBinding

class SigningScreen : AppCompatActivity() {
    private lateinit var binding: ScreenLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScreenLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.sigingDescription.movementMethod = LinkMovementMethod.getInstance()
    }

}