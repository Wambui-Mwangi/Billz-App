package com.mwangi.assesment_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mwangi.assesment_3.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_log_in)
    }

    override fun onResume() {
        super.onResume()
        binding.btLogInn.setOnClickListener {
            logInUser()
        }
    }

    fun logInUser() {
        val name = binding.etUsername.text.toString()
        val password = binding.etpassword.text.toString()
        var error = false

        if (name.isBlank()) {
            binding.tilUsername.error = "Name cannot be empty"
            error = true
        } else {
            binding.tilUsername.error = null
        }

        if (password.isBlank()) {
            binding.tilpassword.error = "Password cannot be empty"
            error = true
        } else {
            binding.tilpassword.error = null
        }

        if (!error) {
            Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_LONG).show()
        }
    }
}