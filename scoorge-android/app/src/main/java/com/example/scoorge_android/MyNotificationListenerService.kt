package com.example.scoorge_android

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class MyNotificationListenerService : NotificationListenerService() {

    private val TAG = "MyNotificationListenerService"

    companion object {
        private const val SAMSUNG_PAY_PACKAGE_NAME = "com.samsung.android.spay"
        private const val KAKAO_PACKAGE_NAME = "com.kakao.talk"
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        val packageName: String = sbn?.packageName ?: ""
        val extras = sbn?.notification?.extras
        val extraTitle: String = extras?.get(Notification.EXTRA_TITLE).toString()
        val extraText: String = extras?.get(Notification.EXTRA_TEXT).toString()

        try {
            if (extraTitle != "null") {
                println(packageName)
                when (packageName) {
                    SAMSUNG_PAY_PACKAGE_NAME
                    -> {
                        val test: List<String> = extraText.split("₩")
                        Log.d(
                            TAG, "onNotificationPosted:\n" +
                                    "PackageName: $packageName\n" +
                                    "Title: $extraTitle\n" +
                                    "Card: ${test[0].trim()}\n"
                                    + "Price: ${test[1].trim()}\n"
                        )
                    }

                    KAKAO_PACKAGE_NAME
                    -> {
                        val test: List<String> = extraText.split("%")
                        Log.d(
                            TAG, "onNotificationPosted:\n" +
                                    "PackageName: $packageName\n" +
                                    "Title: $extraTitle\n" +
                                    "Card: ${test[0].trim()}\n"
                                    + "Price: ${test[1].trim()}\n"
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}