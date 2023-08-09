package com.example.scoorge_android

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebChromeClient.FileChooserParams
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.example.scoorge_android.network.RetrofitService

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "testChannel01"   // Channel for notification
    private var notificationManager: NotificationManager? = null

    /* 이미지 업로드를 위한 변수 */
    private val FILE_CHOOSER_RESULT_CODE = 1
    private var filePathCallback: ValueCallback<Array<Uri>>? = null



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

        webview.webChromeClient = CustomWebChromeClient()
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

    /* 이미지 구현을 위한 클래스 */
    inner class CustomWebChromeClient : WebChromeClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onShowFileChooser(
            webView: WebView?,
            filePathCallback: ValueCallback<Array<Uri>>?,
            fileChooserParams: FileChooserParams?
        ): Boolean {
            this@MainActivity.filePathCallback = filePathCallback

            val intent = fileChooserParams?.createIntent()
            if (intent != null) {
                startActivityForResult(intent, FILE_CHOOSER_RESULT_CODE)
            }

            return true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == FILE_CHOOSER_RESULT_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                if(data != null) {
                    val result = WebChromeClient.FileChooserParams.parseResult(resultCode, data)
                    filePathCallback?.onReceiveValue(result)
                } else {
                    filePathCallback?.onReceiveValue(null)
                }
            } else {
                filePathCallback?.onReceiveValue(null)
            }
            filePathCallback = null
        }
    }

}
