package com.mwangi.assesment_3.api

import com.mwangi.assesment_3.model.LoginRequest
import com.mwangi.assesment_3.model.LoginResponse
import com.mwangi.assesment_3.model.RegisterRequest
import com.mwangi.assesment_3.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

}

