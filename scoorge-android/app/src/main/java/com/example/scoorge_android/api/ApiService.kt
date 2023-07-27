package com.example.scoorge_android.api

import com.example.scoorge_android.model.PayInfo
import com.example.scoorge_android.model.ResponseResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    private val Base_URL: String
        get() = "http://192.168.160.177:8080/"

    @POST("payment-history/{userId}")
    fun postPayInfo(@Path("userId")userId: Long, @Body userInfo: PayInfo) : Call<ResponseResult>
}