package com.example.scoorge_android

import android.content.Context
import android.webkit.JavascriptInterface

class WebAppInterface(private val context: Context) {
    @JavascriptInterface
    fun sendTimeToApp(time: String) {
        // 시간을 받아서 알림 생성 로직 호출
        // time 값은 웹뷰 내에서 선택한 시간
    }
}