package com.example.service.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class BackgroundService: Service() {
    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        player.isLooping = true
        player.start()
        return START_STICKY

        // call stop self to stop the service.
        // stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
       player.stop()
    }
}