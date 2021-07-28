package com.thkim.playground.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thkim.playground.LogT
import com.thkim.playground.databinding.ItemTextBinding

/*
 * Created by Taehyung Kim on 2021-07-27
 */
class TextAdapter : RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    private val dataList = arrayListOf<String>()
    private val alphaList = arrayListOf<Float>()
    var onAlpha: (() -> Pair<Int, Float>)? = null

    class ViewHolder(
        private val binding: ItemTextBinding,
        private val onAlpha: (() -> Pair<Int, Float>)?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, alpha: Float) {
            binding.textView.text = item

            binding.container.alpha = alpha

            onAlpha?.let {
                val pos = it.invoke().first
                val ap = it.invoke().second

                LogT.d("$adapterPosition $pos $ap")
                if (adapterPosition == pos) binding.container.alpha = ap
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onAlpha
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (alphaList.size <= position) alphaList.add(1f)
        holder.bind(dataList[position], alphaList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun getDataList() = dataList

    fun getAlphaList() = alphaList
}