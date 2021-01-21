package com.dualism.dotaheroes.services

import com.dualism.dotaheroes.models.regUser
import com.dualism.dotaheroes.models.userToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

class RestApiService {
    fun addUser(userData: regUser, onResult: (userToken?) -> Unit){
        val retrofit = ServiceBuilder.buildService(registerApi::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<userToken> {
                override fun onFailure(call: Call<userToken>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<userToken>, response: Response<userToken>) {
                    println("RESPONSE BODY: " + response.body())
                    println("RESPONSE: " + response)
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun signInUser(@HeaderMap headers: Map<String, String>, onResult: (userToken?) -> Unit){
        val retrofit = ServiceBuilder.buildService(signInApi::class.java)
        retrofit.signInUser(headers).enqueue(
            object : Callback<userToken> {
                override fun onFailure(call: Call<userToken>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<userToken>, response: Response<userToken>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}