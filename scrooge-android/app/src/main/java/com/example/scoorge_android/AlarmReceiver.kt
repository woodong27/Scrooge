package com.example.scoorge_android

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {
    private var context: Context? = null
    private val channelId = "alarm_channel"

    override fun onReceive(context: Context, intent: Intent) {
        this.context = context
        val busRouteIntent = Intent(context, MainActivity::class.java)
        val imageResId = R.drawable.scrooge_img
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addNextIntentWithParentStack(busRouteIntent)
        val busRoutePendingIntent =
            stackBuilder.getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE)
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground).setDefaults(Notification.DEFAULT_ALL)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setContentTitle("[스크루지 영감]")
                .setContentText("껄껄, 자네 아직 정산을 안 했는가?\n스크루지로 정산하러 가야 할 시간일세.")
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, imageResId))
                .setSmallIcon(imageResId)
                .setContentIntent(busRoutePendingIntent)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        val id = 6
        notificationManager.notify(id, notificationBuilder.build())
    }
}