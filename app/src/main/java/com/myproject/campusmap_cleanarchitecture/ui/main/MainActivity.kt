package com.myproject.campusmap_cleanarchitecture.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.ui.map.MapFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null) {
            val fragment = MapFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        }

    }

}