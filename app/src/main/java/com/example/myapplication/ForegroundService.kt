package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.ServiceCompat

class ForegroundService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    companion object {
        private const val TAG = "RRR"
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind()")
        return Binder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()")
        NotificationHelper.createNotificationChannel(this)
        ServiceCompat.startForeground(this,1,NotificationHelper.buildNotification(this),0)
        mediaPlayer?.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate()")
        mediaPlayer = MediaPlayer.create(this, R.raw.betkhoven)
        mediaPlayer?.isLooping = false
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
        mediaPlayer?.stop()
    }
}