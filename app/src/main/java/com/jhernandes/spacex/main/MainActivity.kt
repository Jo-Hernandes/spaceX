package com.jhernandes.spacex.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jhernandes.spacex.R
import com.jhernandes.spacex.main.ui.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
