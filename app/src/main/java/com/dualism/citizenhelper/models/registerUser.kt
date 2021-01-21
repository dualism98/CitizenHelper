package com.dualism.dotaheroes.models

import com.google.gson.annotations.SerializedName

data class regUser(
    @SerializedName("full_name") val full_name: String,
    @SerializedName("address") val address: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)

data class userToken(
    @SerializedName("token") val token: String?
)