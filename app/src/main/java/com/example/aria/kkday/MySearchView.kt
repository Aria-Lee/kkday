package com.example.aria.kkday

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.SearchView

class MySearchView: SearchView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun requestFocus(direction: Int, previouslyFocusedRect: Rect?): Boolean {
        return super.requestFocus(direction, previouslyFocusedRect)
        this.findViewById<android.support.v7.widget.SearchView.SearchAutoComplete>(R.id.search_src_text).clearFocus()
    }
}