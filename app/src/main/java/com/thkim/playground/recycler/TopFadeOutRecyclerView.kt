package com.thkim.playground.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/*
 * Created by Taehyung Kim on 2021-07-28
 */
class TopFadeOutRecyclerView(
    context: Context,
    attr: AttributeSet? = null
) : RecyclerView(context, attr) {

    override fun getBottomFadingEdgeStrength(): Float {
        return 0F
    }

}