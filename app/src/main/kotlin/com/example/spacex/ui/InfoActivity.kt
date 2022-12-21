package com.example.spacex.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.spacex.R
import com.example.spacex.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListener()
    }

    private fun setClickListener() {
         binding.begin.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.begin -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}