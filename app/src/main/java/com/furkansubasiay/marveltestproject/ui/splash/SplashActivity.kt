package com.furkansubasiay.marveltestproject.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.furkansubasiay.marveltestproject.R
import com.furkansubasiay.marveltestproject.ui.MainActivity
import com.furkansubasiay.marveltestproject.util.NetworkUtils

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        //Splash ekranının görünebilmesi için 1 saniyelik timer konuldu.
        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {

                openActivity()
            }
        }.start()

    }

    private fun openActivity() {
        if(NetworkUtils.isInternetAvailable(applicationContext))
        {
            startActivity(Intent(this,
                MainActivity::class.java))
            this.finish()
        }
        else{
            Toast.makeText(applicationContext,applicationContext.resources.getString(R.string.no_connection_message),Toast.LENGTH_LONG).show()
        }
    }
}
