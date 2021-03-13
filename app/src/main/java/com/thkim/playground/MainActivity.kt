package com.thkim.playground

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.thkim.playground.chap02.course01.CustomViewActivity
import com.thkim.playground.chap02.course02.MiniPaint
import com.thkim.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        setListener()
    }

    private fun setListener() {
        with(binding) {
            btCourse01.setOnClickListener {
                startActivity(Intent(this@MainActivity, CustomViewActivity::class.java))
            }

            btCourse02.setOnClickListener {
                startActivity(Intent(this@MainActivity, MiniPaint::class.java))
            }
        }
    }
}