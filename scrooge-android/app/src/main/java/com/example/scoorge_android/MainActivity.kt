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
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

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

        // 안드로이드와 웹 뷰 간의 브리지 설정
        webview.addJavascriptInterface(AndroidBridge(), "AndroidBridge")

        webview.loadUrl("http://day6scrooge.duckdns.org:3000/")


//        /* 알림 */
//        createNotificationChannel(CHANNEL_ID, "testChannel", "this is a test Channel")
//        val button = findViewById<Button>(R.id.noti_btn)
//        button.setOnClickListener {
//            displayNotification()
//        }
    }

//    private fun displayNotification() {
//        val notificationId = 45
//
//        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle("Title")
//            .setContentText("오늘도찾아온카드 ₩7,500")
//            .build()
//
//        notificationManager?.notify(notificationId, notification)
//    }
//
//    private fun createNotificationChannel(channelId: String, name: String, channelDescription: String) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(channelId, name, importance).apply {
//                description = channelDescription
//            }
//            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager?.createNotificationChannel(channel)
//        }
//    }

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

// 브릿지를 통해 웹 앱과 상호작용하기 위한 브릿지 클래스
class AndroidBridge {
    @JavascriptInterface
    fun sendJwtTokenToAndroid(jwtToken: String){
        Log.d("check", jwtToken);
    }
}