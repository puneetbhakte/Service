package com.example.service

import android.app.ActivityManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.service.databinding.ActivityFourgroundBinding
import com.example.service.service.FourgroundService


class Fourground : AppCompatActivity() {
    private lateinit var binding:ActivityFourgroundBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFourgroundBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btStart.setOnClickListener {

            if (!foregroundServiceRunning()){
                val intent = Intent(this,FourgroundService::class.java)
                startForegroundService(intent)
            }else{
                Toast.makeText(this, "Fourground Service is already running", Toast.LENGTH_SHORT).show()
            }

        }
        binding.btStop.setOnClickListener {
            val intent = Intent(this,FourgroundService::class.java)
            stopService(intent)
        }

    }

    fun foregroundServiceRunning(): Boolean {
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (FourgroundService::class.java.getName() == service.service.className) {
                return true
            }
        }
        return false
    }
}