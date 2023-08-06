package com.example.scoorge_android.model

data class PayInfo (
    val usedAt: String,
    val cardName: String,
    val amount: Int,
)

data class ResponseResult (
    val success: Int,
    val id: Int,
)