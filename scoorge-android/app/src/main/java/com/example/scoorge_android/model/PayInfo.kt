package com.example.scoorge_android.model

data class PayInfo (
    val user_id: String,
    val used_at: String,
    val card_name: String,
    val amount: Int,
)

data class ResponseResult (
    val success: Int,
)