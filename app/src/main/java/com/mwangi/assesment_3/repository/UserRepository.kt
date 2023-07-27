package com.mwangi.assesment_3.repository

import com.mwangi.assesment_3.api.ApiClient
import com.mwangi.assesment_3.api.ApiInterface
import com.mwangi.assesment_3.model.LoginRequest
import com.mwangi.assesment_3.model.LoginResponse
import com.mwangi.assesment_3.model.RegisterRequest
import com.mwangi.assesment_3.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)

    suspend fun register(registerRequest: RegisterRequest): Response<RegisterResponse>{
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse>{
        return withContext(Dispatchers.IO){
            apiClient.loginUser(loginRequest)
        }
    }
}
