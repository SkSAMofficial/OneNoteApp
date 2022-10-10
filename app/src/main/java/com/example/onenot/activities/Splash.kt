package com.example.onenot.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import com.example.onenot.R
import java.util.*
import kotlin.concurrent.timerTask

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

            Timer().schedule(object : TimerTask(){
                override fun run() {
                    clearAct();
                }
            },2000);

    }

    private fun clearAct() {
        startActivity(Intent(this@Splash,MainActivity::class.java))
        this.finish();
    }
}