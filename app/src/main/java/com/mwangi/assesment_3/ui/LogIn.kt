package com.mwangi.assesment_3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mwangi.assesment_3.databinding.ActivityLogInBinding
import com.mwangi.assesment_3.model.LoginRequest
import com.mwangi.assesment_3.model.RegisterRequest
import com.mwangi.assesment_3.viewmodel.UserViewModel

class LogIn : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btLogInn.setOnClickListener {
            logInUser()
        }

        binding.tvSign.setOnClickListener { startActivity(Intent(this, SignUp::class.java)) }

        userViewModel.loginLiveData.observe(this, Observer { loginResponse->
            Toast.makeText(this, loginResponse.message, Toast.LENGTH_LONG).show()
            binding.pbloginLoad.visibility = View.GONE

            startActivity(Intent(this, Home::class.java))
        })

        userViewModel.errLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbloginLoad.visibility = View.GONE
        })


    }

    fun logInUser() {
        val email = binding.etemail.text.toString()
        val password = binding.etpassword.text.toString()
        var error = false

        if (email.isBlank()) {
            binding.tilemail.error = "Name cannot be empty"
            error = true
        } else {
            binding.tilemail.error = null
        }

        if (password.isBlank()) {
            binding.tilpassword.error = "Password cannot be empty"
            error = true
        }
        else {
            binding.tilpassword.error = null
        }

        if (!error) {
            val logInUser = LoginRequest(
                email = email,
                password = password
            )
            binding.pbloginLoad.visibility = View.VISIBLE
            userViewModel.loginUser(logInUser)
        }
    }
}