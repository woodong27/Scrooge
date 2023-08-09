package com.example.scoorge_android

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.core.app.NotificationManagerCompat
import com.example.scoorge_android.network.RetrofitService

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "testChannel01"   // Channel for notification
    private var notificationManager: NotificationManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!isNotificationPermissionGranted()) {
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        }

        val webview = findViewById<WebView>(R.id.webview)
        webview.apply{
            webViewClient= WebViewClient()
            settings.javaScriptEnabled=true
        }
        webview.settings.javaScriptEnabled = true

        val androidBridge = AndroidBridge()
        webview.addJavascriptInterface(androidBridge, "AndroidBridge")

        // 안드로이드와 웹 뷰 간의 브리지 설정
//        webview.addJavascriptInterface(AndroidBridge(), "AndroidBridge")

        webview.loadUrl("https://day6scrooge.duckdns.org/")

//        val textView = findViewById<TextView>(R.id.textView)
//        textView.setText(MyNotificationListenerService.Test)


    }

    inner class AndroidBridge {
        @JavascriptInterface
        fun sendJwtTokenToAndroid(jwtToken: String) {
            Log.d("check", jwtToken)
            RetrofitService.setAuthToken(jwtToken)
        }
    }

    private fun isNotificationPermissionGranted(): Boolean {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            return notificationManager.isNotificationListenerAccessGranted(ComponentName(application, MyNotificationListenerService::class.java))
        }
        else {
            return NotificationManagerCompat.getEnabledListenerPackages(applicationContext).contains(applicationContext.packageName)
        }
    }

}
