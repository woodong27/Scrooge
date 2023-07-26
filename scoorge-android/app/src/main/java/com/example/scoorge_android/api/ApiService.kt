package com.example.scoorge_android.api

import com.example.scoorge_android.model.PayInfo
import com.example.scoorge_android.model.ResponseResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    private val Base_URL: String
        get() = "http://10.0.2.2:8080/"

    @POST("payment-history")
    fun postPayInfo(@Body userInfo: PayInfo) : Call<ResponseResult>
}