package com.dualism.citizenhelper.models

import com.google.gson.annotations.SerializedName


data class RegUser(
    @SerializedName("full_name") val full_name: String,
    @SerializedName("address") val address: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)

data class UserResponse(
    @SerializedName("token") val token: String,
    @SerializedName("email") val email: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("address") val address: String,
)

data class RegisterError(
    @SerializedName("codes") val codes: List<String>,
)