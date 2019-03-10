package com.example.aria.kkday

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView

class CusImageView : ImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //获取View的宽高
//        var width = getWidth()
//        var height = getHeight()
//
//        var colorStart = Color.argb(200, 60, 60, 60)
//        var color1 = Color.argb(0, 60, 60, 60)
//        var colorEnd = Color.argb(200, 60, 60, 60)
//
//        var paint = Paint()
//        var backGradient = LinearGradient(
//            0f, 0f, 0f, height.toFloat() / 2, intArrayOf(colorStart, color1),
//            floatArrayOf(
//                0.5f, 0.5f
//            ), Shader.TileMode.MIRROR
//        )
//        paint.setShader(backGradient)
//        canvas!!.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
//
    }
}
