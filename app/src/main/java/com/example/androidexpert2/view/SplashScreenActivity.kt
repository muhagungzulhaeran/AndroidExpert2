package com.example.androidexpert2.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.androidexpert2.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            binding = ActivitySplashScreenBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val splashScreenDuration = 3000L

            Handler(Looper.getMainLooper()).postDelayed({
                val intentt = Intent(this, MainActivity::class.java)
                startActivity(intentt)
                finish()
            }, SPLASH_SCREEN_DURATION)
        }
        supportActionBar?.hide()

    }

    companion object {
        const val SPLASH_SCREEN_DURATION = 3000L
    }
}