package com.example.scoorge_android

import android.Manifest
import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Data
import androidx.work.WorkManager
import com.example.scoorge_android.network.RetrofitService
import java.util.Calendar


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
        webview.addJavascriptInterface(WebAppInterface(this), "AndroidInterface")


        val androidBridge = AndroidBridge()
        webview.addJavascriptInterface(androidBridge, "AndroidBridge")

        // 알림 채널 생성 및 등록
        createNotificationChannel()

        webview.webChromeClient = CustomWebChromeClient()
        webview.loadUrl("https://day6scrooge.duckdns.org/")
    }


    /* 알림 관련 */

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Test Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }




    inner class WebAppInterface(private val context: Context) {
        @JavascriptInterface
        fun sendTimeToApp(time: String) {
            val selectedTime = parseSelectedTime(time) // 시간 파싱

            if (selectedTime != null) {
                // 현재 시간 가져오기
                val currentTime = Calendar.getInstance()
                val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
                val currentMinute = currentTime.get(Calendar.MINUTE)

                // 선택한 시간의 시와 분 분리
                val selectedHour = selectedTime[0]
                val selectedMinute = selectedTime[1]

                Log.d("Check", currentTime.toString())
                Log.d("Check", currentHour.toString())
                Log.d("Check", currentMinute.toString())

                Log.d("Check", selectedHour.toString())
                Log.d("Check", selectedMinute.toString())

                // 시간 비교 및 알림 생성
                if (currentHour == selectedHour && currentMinute == selectedMinute) {
                }
            }
        }


        private fun parseSelectedTime(time: String): IntArray? {
            try {
                val splitTime = time.split(":")
                val hour = splitTime[0].toInt()
                val minute = splitTime[1].toInt()
                return intArrayOf(hour, minute)
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
        }
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
