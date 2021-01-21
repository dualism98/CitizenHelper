package com.dualism.dotaheroes.services
import com.dualism.dotaheroes.models.regUser
import com.dualism.dotaheroes.models.userToken
import retrofit2.Call
import retrofit2.http.*

interface registerApi {
    @Headers("Content-Type: application/json")
    @POST("/api/v1/user/")
    fun addUser(@Body userData: regUser): Call<userToken>
}

interface signInApi {
    @GET("/api/v1/user/")
    fun signInUser(@HeaderMap headers: Map<String, String>): Call<userToken>
}
