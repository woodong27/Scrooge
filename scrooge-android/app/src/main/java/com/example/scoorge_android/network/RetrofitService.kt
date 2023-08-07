package com.example.scoorge_android.network

import com.example.scoorge_android.MainActivity
import com.example.scoorge_android.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val Base_URL = "http://day6scrooge.duckdns.org:8081"

    private var authToken = ""

    private fun provideOkHttpClient(authToken: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(authToken))
            .build()
    }

    fun setAuthToken(token: String) {
        authToken = token
    }

    val instance : ApiService by lazy {
        val retrofit =
            Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(authToken))
            .build()
        retrofit.create(ApiService::class.java)
    }
}