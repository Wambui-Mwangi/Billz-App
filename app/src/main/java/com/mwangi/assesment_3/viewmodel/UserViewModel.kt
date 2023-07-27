package com.mwangi.assesment_3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwangi.assesment_3.model.LoginRequest
import com.mwangi.assesment_3.model.LoginResponse
import com.mwangi.assesment_3.model.RegisterRequest
import com.mwangi.assesment_3.model.RegisterResponse
import com.mwangi.assesment_3.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val userRepo = UserRepository()
    val registrationLiveData = MutableLiveData<RegisterResponse>()
    val loginLiveData = MutableLiveData<LoginResponse>()
    val errLiveData = MutableLiveData<String>()


    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepo.register(registerRequest)
            if (response.isSuccessful){
                registrationLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }

    }

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepo.login(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}