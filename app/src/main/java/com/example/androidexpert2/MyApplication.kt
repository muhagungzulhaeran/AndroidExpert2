package com.example.androidexpert2

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.androidexpert2.core.utils.DarkMode
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        preferences.getString(
            getString(R.string.prefKeyDark),
            getString(R.string.prefDarkFollowSystem)
        )?.apply {
            val modee = DarkMode.valueOf(this.uppercase(Locale.US))
            AppCompatDelegate.setDefaultNightMode(modee.value)
        }
    }
}