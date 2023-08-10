package com.example.scoorge_android.network

import android.util.Log
import com.example.scoorge_android.MainActivity
import com.example.scoorge_android.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val Base_URL = "https://day6scrooge.duckdns.org/api/"

    private var authToken = ""

    private var retrofit: Retrofit? = null

    private fun provideOkHttpClient(authToken: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(authToken))
            .build()
    }

    fun setAuthToken(token: String) {
        authToken = token
        Log.d("토큰 변경 시 authToken 갱신", authToken)
        retrofit = null // 토큰이 변경되면 retrofit 인스턴스를 다시 생성해야 하므로 null로 설정
    }

    val instance : ApiService
        get() {
            if(retrofit == null) {
                retrofit =
                    Retrofit.Builder()
                        .baseUrl(Base_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(provideOkHttpClient(authToken))
                        .build()
            }
            Log.d("RETROFIT CHECK", authToken)
            return retrofit!!.create(ApiService::class.java)
        }
}