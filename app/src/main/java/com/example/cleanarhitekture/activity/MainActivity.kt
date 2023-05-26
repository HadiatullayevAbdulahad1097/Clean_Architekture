package com.example.cleanarhitekture.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarhitekture.R
import com.example.cleanarhitekture.ui.utils.UtilsFunction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}