  package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import java.util.concurrent.TimeUnit

  class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        simpleWork()
        myWorkManager()
        var viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        var PressMe = findViewById<TextView>(R.id.pressme);
        PressMe.setOnClickListener{
            viewModel.addNumber()


        }
    }
      private fun myWorkManager() {
          val constraints = Constraints.Builder()
//              .setRequiresCharging(false)
//              .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
//              .setRequiresBatteryNotLow(true)
              .build()
          val myRequest = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES).setConstraints(constraints)
              .build()
          WorkManager.getInstance(this).enqueueUniquePeriodicWork("myID", ExistingPeriodicWorkPolicy.KEEP, myRequest)
      }





      private fun simpleWork() {
          val c = 16.2 * 3600000;
          var currentTime= System.currentTimeMillis();
          val specificTimeToTrigger =  c;
          val delayToPass = specificTimeToTrigger - currentTime;
       val mRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorker>().setInitialDelay(delayToPass.toLong(), TimeUnit.MILLISECONDS).build()
        WorkManager.getInstance(this).enqueue(mRequest)
    }
}