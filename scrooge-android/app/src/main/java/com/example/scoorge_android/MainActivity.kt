package com.example.scoorge_android

import android.app.Activity
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.example.scoorge_android.network.RetrofitService
import java.util.Calendar
import java.util.TimeZone


class MainActivity : AppCompatActivity() {
    /* 이미지 업로드를 위한 변수 */
    private val FILE_CHOOSER_RESULT_CODE = 1
    private var filePathCallback: ValueCallback<Array<Uri>>? = null

    private lateinit var notificationManager: NotificationManagerCompat

    // BGM 구현 위한 변수
    private lateinit var mediaPlayer: MediaPlayer
    private var isBgmOn: Boolean = true

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
        webview.getSettings().setDomStorageEnabled(true);

        val androidBridge = AndroidBridge()
        webview.addJavascriptInterface(androidBridge, "AndroidBridge")

        val androidAlarmAllow = AndroidAlarmAllow()
        webview.addJavascriptInterface(androidAlarmAllow ,"AndroidAlarmAllow")
        webview.addJavascriptInterface(WebAppInterface(this), "AndroidInterface")

        webview.webChromeClient = CustomWebChromeClient()
        webview.loadUrl("https://day6scrooge.duckdns.org/")

        val androidSound = AndroidSound()
        webview.addJavascriptInterface(androidSound, "AndroidSound")
    }


    /* 알림 관련 */

    inner class WebAppInterface(private val context: Context) {
        @JavascriptInterface
        fun sendTimeToApp(time: String) {
            Log.d("TAG", time) //12:48
            val parts = time.split(":")
            val hours = parts[0].toInt()
            val minutes = parts[1].toInt()
            scheduleNotification(hours, minutes)
        }
        @JavascriptInterface
        fun cancelNotification() {
            Log.d("TAG", "삭제")
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            alarmManager.cancel(pendingIntent);
        }
    }

    private fun scheduleNotification(hourOfDay: Int, minute: Int) {
        Log.d("TAG", "Scheduled time: $hourOfDay $minute")

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val currentTimeMillis = System.currentTimeMillis()
        val timeZone = TimeZone.getTimeZone("Asia/Seoul")

        val calendar = Calendar.getInstance(timeZone).apply {
            timeInMillis = currentTimeMillis
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (timeInMillis <= currentTimeMillis) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }

        // 시간 간격을 24시간으로 설정하여 매일 반복되도록 설정합니다.
//        val intervalMillis: Long = 24 * 60 * 60 * 1000 //하루
        val intervalMillis: Long = 1 * 60 * 1000 // 테스트용 1분 간격
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            intervalMillis,
            pendingIntent
        )
    }


    inner class AndroidBridge {
        @JavascriptInterface
        fun sendJwtTokenToAndroid(jwtToken: String) {
            Log.d("check", jwtToken)
            RetrofitService.setAuthToken(jwtToken)
        }
    }

    inner class AndroidAlarmAllow {
        @JavascriptInterface
        fun sendAllowToApp() {
            val hasPostNotificationPermission = NotificationManagerCompat.from(applicationContext).areNotificationsEnabled()
            if(!hasPostNotificationPermission) {
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                    .putExtra(Settings.EXTRA_APP_PACKAGE, "com.example.scoorge_android")
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                applicationContext.startActivity(intent)
            }
        }
    }

    /* 소리 구현 위한 클래스 */
    inner class AndroidSound {
        @JavascriptInterface
        fun sendSoundToggleToAndroid(isSoundOn: Boolean) {
            if(isSoundOn) {
                Log.d("CHECK", "소리를 꺼요")
                isBgmOn = false
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            else {
                Log.d("CHECK", "소리를 켜요")
                isBgmOn = true
                mediaPlayer = MediaPlayer.create(applicationContext, R.raw.bgm)
                mediaPlayer.isLooping = true
                mediaPlayer.start()
            }
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
