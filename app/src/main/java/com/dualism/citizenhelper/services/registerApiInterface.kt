package com.dualism.citizenhelper.services
import com.dualism.citizenhelper.models.RegUser
import com.dualism.citizenhelper.models.UserResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface AuthApi {
    @GET("/api/v1/user/")
    fun signInUser(@HeaderMap headers: Map<String, String>): Call<UserResponse>
}

interface RegApi {
    @Headers("Content-Type: application/json")
    @POST("/api/v1/user/")
    fun addUser(@Body userData: RegUser): Call<JsonObject>
}
