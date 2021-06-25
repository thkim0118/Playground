package com.thkim.playground.activity

import android.os.Bundle
import android.util.Log
import com.thkim.playground.base.BaseActivity
import com.thkim.playground.databinding.ActivityTwoBinding

class TwoActivity : BaseActivity<ActivityTwoBinding>(ActivityTwoBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent != null) {
            Log.d("terry" , "data -> ${intent.extras}")
            Log.d("terry" , "data -> ${intent.extras?.getInt("test")}")
       }

        binding.btFinish.setOnClickListener {
            finish()
        }
    }
}