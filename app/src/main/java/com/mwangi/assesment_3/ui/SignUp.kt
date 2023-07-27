package com.mwangi.assesment_3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mwangi.assesment_3.databinding.ActivitySignUpBinding
import com.mwangi.assesment_3.model.RegisterRequest
import com.mwangi.assesment_3.viewmodel.UserViewModel

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btSignUp.setOnClickListener {
            signUpUser()
        }

        binding.btLogin.setOnClickListener { startActivity(Intent(this, LogIn::class.java)) }

        userViewModel.registrationLiveData.observe(this, Observer { regResponse ->
            Toast.makeText(this, regResponse.message, Toast.LENGTH_LONG).show()
            binding.pbRegister.visibility = View.GONE

            startActivity(Intent(this, LogIn::class.java))
        })

        userViewModel.errLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbRegister.visibility = View.GONE
        })
    }

    fun signUpUser() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val phone = binding.etPhoneNumber.text.toString()
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        var error = false


        if (firstName.isBlank()) {
            binding.tilUserName.error = "Name cannot be empty"
            error = true
        }
        else {
            binding.tilUserName.error = null
        }

        if (lastName.isBlank()) {
            binding.tilLastName.error = "Name cannot be empty"
            error = true
        }
        else {
            binding.tilUserName.error = null
        }

        if (phone.isBlank()) {
            binding.tilPhoneNumber.error = "Phone number cannot be empty"
            error = true
        }
        else {
            binding.tilPhoneNumber.error = null
        }

        if (email.isBlank()) {
            binding.tilEmailAddress.error = "Email Address cannot be empty"
            error = true
        }
        else {
            binding.tilEmailAddress.error = null
        }

        if (password.isBlank()) {
            binding.tilPassword.error = "Password cannot be empty"
            error = true
        }
        else {
            binding.tilPassword.error = null
        }

        if (password != confirmPassword) {
            binding.tilConfirmPassword.error = "Passwords do not match"
            error = true
        }
        else {
            binding.tilConfirmPassword.error = null
        }

        if (!error) {
            val registerRequest = RegisterRequest(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phone,
                email = email,
                password = password
            )
            binding.pbRegister.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)

        }
    }
}