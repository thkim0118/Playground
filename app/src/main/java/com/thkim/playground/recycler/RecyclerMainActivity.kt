package com.thkim.playground.recycler

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thkim.playground.LogT
import com.thkim.playground.base.BaseActivity
import com.thkim.playground.databinding.ActivityRecyclerMainBinding

class RecyclerMainActivity :
    BaseActivity<ActivityRecyclerMainBinding>(ActivityRecyclerMainBinding::inflate) {

    val text = "TEST MESSAGE !!!"
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textAdapter = TextAdapter()
        binding.recyclerView.apply {
            adapter = textAdapter
            layoutManager =
                LinearLayoutManager(this@RecyclerMainActivity, RecyclerView.VERTICAL, false).apply {
                    stackFromEnd = true
                }
        }

        binding.addButton.setOnClickListener {
            count += 1
            textAdapter.getDataList().add("$count::$text")
            textAdapter.notifyItemRangeChanged(textAdapter.itemCount - 1, 1)
            binding.recyclerView.scrollToPosition(textAdapter.itemCount - 1)
        }
    }
}