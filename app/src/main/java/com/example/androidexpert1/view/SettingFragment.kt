package com.example.androidexpert1.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.androidexpert1.R
import com.example.androidexpert1.core.utils.DarkMode
import java.util.Locale

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val preff = findPreference<ListPreference>(getString(R.string.prefKeyDark))
        preff?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                val value = newValue as String
                val selectedMode = when (value.uppercase(Locale.US)) {
                    DarkMode.ON.name -> DarkMode.ON
                    DarkMode.OFF.name -> DarkMode.OFF
                    else -> DarkMode.FOLLOW_SYSTEM
                }
                updateThemes(selectedMode.value)
                true
            }
    }

    private fun updateThemes(mode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(mode)
        requireActivity().recreate()
        return true
    }


}