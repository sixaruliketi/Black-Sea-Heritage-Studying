package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.LanguageChangeTools.LocaleHelper
import com.example.myapplication.LanguageChangeTools.SharedPreferencesUtils.getLanguagePosition
import com.example.myapplication.LanguageChangeTools.SharedPreferencesUtils.setLanguageCode
import com.example.myapplication.LanguageChangeTools.SharedPreferencesUtils.setLanguagePosition
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        changeRadioButtonViews(getLanguagePosition(this))

        binding.languageRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            onRadioButtonClicked(radio)
        }

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase!!))
    }


    private fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_english ->
                    if (checked) {
                        // English language selected
                        setLanguageCode(this, "en")
                        setLanguagePosition(this, 0)
                        recreate()

                    }
                R.id.radio_geo ->
                    if (checked) {
                        // Urdu language selected
                        setLanguageCode(this, "geo")
                        setLanguagePosition(this, 1)
                        recreate()
                    }

            }
        }
    }

    private fun changeRadioButtonViews(position:Int){
        when(position){
            0 -> binding.radioEnglish.isChecked = true
            1 -> binding.radioGeo.isChecked = true
        }
    }
}