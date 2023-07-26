package com.example.scoorge_android.network

import com.example.scoorge_android.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val Base_URL = "http://10.0.2.2:8080/"

    val instance : ApiService by lazy {
        val retrofit =
            Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}