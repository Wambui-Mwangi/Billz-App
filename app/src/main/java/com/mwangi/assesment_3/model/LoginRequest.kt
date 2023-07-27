package com.mwangi.assesment_3.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    var email:String,
    var password:String
)
