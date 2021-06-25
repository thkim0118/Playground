package com.thkim.playground.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/*
 * Created by Taehyung Kim on 2021-06-25
 */
typealias ActivityInflater<B> = (LayoutInflater) -> B

abstract class BaseActivity<B : ViewBinding>(
    private val inflate: ActivityInflater<B>
) : AppCompatActivity() {

    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflate.invoke(layoutInflater)

        setContentView(binding.root)
    }
}