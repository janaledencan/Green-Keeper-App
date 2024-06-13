package com.jana.greenkeeper.viewmodel

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.jana.greenkeeper.viewmodel.notification.Notification
import com.jana.greenkeeper.viewmodel.notification.channelID
import com.jana.greenkeeper.viewmodel.notification.messageExtra
import com.jana.greenkeeper.viewmodel.notification.notificationID
import com.jana.greenkeeper.viewmodel.notification.titleExtra
import java.util.Date


class NotificationViewModel () : ViewModel() {

    @SuppressLint("ScheduleExactAlarm")
    fun scheduleNotification(
        context: Context,
        title: String,
        message: String,
        timeInMillis: Long
    ) {
        val intent = Intent(context, Notification::class.java).apply {
            putExtra(titleExtra, title)
            putExtra(messageExtra, message)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            pendingIntent
        )

        val date = Date(timeInMillis)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(context)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(context)

        Toast.makeText(
            context,
            "Notification scheduled for ${dateFormat.format(date)} ${timeFormat.format(date)}",
            Toast.LENGTH_LONG
        ).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(context: Context) {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance).apply {
            description = desc
        }
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
