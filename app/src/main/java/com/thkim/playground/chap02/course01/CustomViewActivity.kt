package com.thkim.playground.chap02.course01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thkim.playground.R

class CustomViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
    }
}