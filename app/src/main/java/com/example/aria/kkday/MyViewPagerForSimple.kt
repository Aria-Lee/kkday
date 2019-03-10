package com.example.aria.kkday

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.logging.Handler

class MyViewPagerForSimple : ViewPager {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var height = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(
                widthMeasureSpec,
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            height = child.measuredHeight/2
        }
        var newHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            height,
            View.MeasureSpec.EXACTLY
        )
//        android.os.Handler().post {
//            Log.d("****** hello james ", " here is request Layout")
//            this.requestLayout()
//        }
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }
}