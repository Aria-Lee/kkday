package com.example.aria.kkday

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.detail_content_viewpager_item.view.*
import kotlinx.android.synthetic.main.item_view_detail.view.*
import kotlinx.android.synthetic.main.item_view_simple.view.*

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