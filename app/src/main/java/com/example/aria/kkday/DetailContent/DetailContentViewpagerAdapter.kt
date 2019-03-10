package com.example.aria.kkday.DetailContent

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.aria.kkday.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.detail_content_viewpager_item.view.*

class DetailContentViewpagerAdapter(var context: Context, var list: List<Int>) : PagerAdapter() {

    override fun isViewFromObject(v: View, any: Any): Boolean {
        return v == any
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.detail_content_viewpager_item, container, false)
        Glide.with(context).load(list[position])
            .apply(bitmapTransform(RoundedCornersTransformation(15, 0, RoundedCornersTransformation.CornerType.ALL)))
            .into(view.detailContentViewPagerImg)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}