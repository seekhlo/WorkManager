package com.example.workmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.from
import androidx.lifecycle.ViewModelProvider.Factory.Companion.from
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.reflect.Parameter
import java.util.Date.from

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params){

    companion object{
        const val ChannelID = "CHANNEL_ID"
        const val NotificationID = 1
    }

    override fun doWork(): Result {
        Log.d("TAG", "dowork: Success")

//        showNotification()
        return Result.success()
    }

    private fun showNotification(){
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }


        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)


//        val notification = NotificationCompat.Builder(applicationContext, 0, intent, 0 )
//          val notification = NotificationCompat.Builder(applicationContext, 0).  setSmallIcon(androidx.loader.R.drawable.notification_bg).setContentTitle("NewTask")
//            .setContentText("Usama is a nice person").setPriority(NotificationCompat.PRIORITY_MAX)
//            .setAutoCancel(true)
//            .setContentIntent(pendingIntent)
        val notification = NotificationCompat.Builder(applicationContext, ChannelID).setSmallIcon(
            androidx.core.R.drawable.notification_bg)
            .setContentText("Usama is a nice person").setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)


//

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelName= "Channel Name"
            val channelDescription = "Channel Description"
            val channelImportance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(ChannelID, channelName, channelImportance).apply {
                description = channelDescription

            }
            val NotificationManager = applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

          with(NotificationManagerCompat.from(applicationContext)){
              notify(NotificationID, notification.build())
          }
        }


        val builder= NotificationCompat.Builder(applicationContext, "my_id_unique")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Life is good")
            .setContentText("Usama is always a good person")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)


        with(NotificationManagerCompat.from(applicationContext)){
            notify(1, builder.build())
        }

    }
}
