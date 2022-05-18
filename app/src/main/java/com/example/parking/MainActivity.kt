package com.example.parking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parking.ui.user.UserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.user_menu_container, UserFragment.newInstance())
                    .commitNow()
        }
    }
}