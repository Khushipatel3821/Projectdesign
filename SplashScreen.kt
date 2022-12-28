package com.application.e_greetings.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.application.e_greetings.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed(Runnable {

            startActivity(Intent(this,LoginActivity::class.java))

        },3000)
        // 3000 is the delayed time in milliseconds.
    }
}