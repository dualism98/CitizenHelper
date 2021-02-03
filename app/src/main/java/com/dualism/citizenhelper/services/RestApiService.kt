package com.dualism.citizenhelper.services

import android.util.Log
import com.dualism.citizenhelper.models.RegUser
import com.dualism.citizenhelper.models.RegisterError
import com.dualism.citizenhelper.models.UserResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.HeaderMap
import java.io.IOException


class RestApiService {
    fun addUser(userData: RegUser, onResult: (Boolean, String?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RegApi::class.java)
        retrofit.addUser(userData)
            .enqueue(
                    object : Callback<JsonObject> {
                        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                            onResult(false, null)
                        }

                        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                            if (response.code() == 200) {
                                val addedUser: UserResponse? = Gson().fromJson(response.body(), UserResponse::class.java)
                                onResult(true, response.body().toString())
                            } else {
                                val gson = GsonBuilder().create()
                                try {
                                    val mError: RegisterError = gson.fromJson(response.errorBody()!!.string(), RegisterError::class.java)
                                    val message = errorTypes[mError.codes[0]]
                                    onResult(false, message)
                                } catch (e: IOException) {
                                    // handle failure to read error
                                }
                            }
                        }
                    }
            )
    }

    fun signInUser(@HeaderMap headers: Map<String, String>, onResult: (UserResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(AuthApi::class.java)
        retrofit.signInUser(headers).enqueue(
                object : Callback<UserResponse> {
                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        onResult(null)
                    }

                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        println("BODY: " + response.body())
                        val addedUser = response.body()
                        onResult(addedUser)
                    }
                }
        )
    }
}