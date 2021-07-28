package com.thkim.playground

import android.content.Intent
import android.os.Bundle
import com.thkim.playground.activity.OneActivity
import com.thkim.playground.base.BaseActivity
import com.thkim.playground.chap02.course01.CustomViewActivity
import com.thkim.playground.chap02.course02.MiniPaint
import com.thkim.playground.chap02.course03.ClippingActivity
import com.thkim.playground.chap02.course04.FindMeActivity
import com.thkim.playground.databinding.ActivityMainBinding
import com.thkim.playground.recycler.RecyclerMainActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

            btCourse03.setOnClickListener {
                startActivity(Intent(this@MainActivity, ClippingActivity::class.java))
            }

            btCourse04.setOnClickListener {
                startActivity(Intent(this@MainActivity, FindMeActivity::class.java))
            }

            btActivityResult.setOnClickListener {
                startActivity(Intent(this@MainActivity, OneActivity::class.java))
            }

            recyclerTextButton.setOnClickListener {
                startActivity(Intent(this@MainActivity, RecyclerMainActivity::class.java))
            }
        }
    }
}