package com.mwangi.assesment_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mwangi.assesment_3.databinding.ActivityLogInBinding
import com.mwangi.assesment_3.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_log_in)
    }

    override fun onResume() {
        super.onResume()
        binding.btSignUp.setOnClickListener {
            logInUser()
        }

        binding.btLogIn.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }

        fun logInUser(){
            val name = binding.etUserName.text.toString()
            val phone = binding.etPhoneNumber.text.toString()
            val email = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            var error = false

            if (name.isBlank()){
            binding.tilUserName.error = "Name cannot be empty"
            error = true
            }else{
                    binding.tilUserName.error = null
                }

            if (phone.isBlank()){
                binding.tilPhoneNumber.error = "Phone number cannot be empty"
                error = true
            } else{
            binding.tilPhoneNumber.error = null
            }

            if (email.isBlank()){
                binding.tilEmailAddress.error = "Email Address cannot be empty"
                error = true
            } else{
                binding.tilEmailAddress.error = null
            }

            if (password.isBlank()){
                binding.tilPassword.error = "Password cannot be empty"
                error = true
            } else{
                binding.tilPassword.error = null
            }

            if (password!=confirmPassword){
                binding.tilConfirmPassword.error = "Passwords do not match"
                error = true
            } else{
                binding.tilConfirmPassword.error = null
            }

        if (!error){
            Toast.makeText(this, "Successfully signed up!", Toast.LENGTH_LONG).show()
        }
    }
}