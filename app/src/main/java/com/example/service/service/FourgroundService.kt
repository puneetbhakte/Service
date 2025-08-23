package com.example.service.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import androidx.core.app.NotificationCompat
import com.example.service.MainActivity
import com.example.service.R

class FourgroundService:Service() {
    private lateinit var player: MediaPlayer
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val intent = Intent(this, MainActivity::class.java).apply {
            // These flags prevent creating a new task that kills the service
            this.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )


         val notification = NotificationCompat.Builder(this,"Fourgorund_Service")
             .setSmallIcon(R.drawable.ic_launcher_foreground)
             .setContentTitle("Ringtone playing")
             .setContentText("Ringing")
             .setContentIntent(pendingIntent)
             .build()
        startForeground(1,notification)
       player = MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI)
        player.isLooping = true
        player.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }
}