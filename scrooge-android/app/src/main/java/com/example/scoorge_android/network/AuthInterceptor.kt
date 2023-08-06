package com.example.scoorge_android.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val authToken: String) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        Log.d("TAG", authToken)
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $authToken")
            .build()
        return chain.proceed(request)
    }

}