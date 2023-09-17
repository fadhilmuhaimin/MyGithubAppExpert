package com.fadhil.mygithubapp.ui.setting

import android.content.Context
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.fadhil.mygithubapp.Utils.SettingPreferences
import com.fadhil.mygithubapp.databinding.ActivitySettingBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SettingActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )
        binding = ActivitySettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        mainViewModel.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            mainViewModel.saveThemeSetting(isChecked)
        } 
        setContentView(binding.root)
    }
}