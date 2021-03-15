package com.thkim.playground.chap02.course03

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ClippingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ClippingView(this))
    }
}