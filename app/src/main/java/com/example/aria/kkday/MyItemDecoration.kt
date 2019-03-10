package com.example.aria.kkday

import android.R.attr.top
import android.R.attr.bottom
import android.R.attr.right
import android.R.attr.left
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class MyItemDecoration(var mSpace: Int, var firstSpace: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = mSpace
        outRect.right = mSpace
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = firstSpace
        }

    }
}