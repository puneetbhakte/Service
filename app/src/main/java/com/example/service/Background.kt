package com.example.service

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.service.databinding.ActivityBackgroundBinding
import com.example.service.service.BackgroundService

class Background : AppCompatActivity() {
    private lateinit var binding: ActivityBackgroundBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBackgroundBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btStart.setOnClickListener {
            val intent = Intent(this,BackgroundService::class.java)
            startService(intent)
        }

        binding.btStop.setOnClickListener {
            val intent = Intent(this,BackgroundService::class.java)
            stopService(intent)
        }

    }
}