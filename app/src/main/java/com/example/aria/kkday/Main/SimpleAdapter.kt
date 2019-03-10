package com.example.aria.kkday.Main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.aria.kkday.R
import com.example.aria.kkday.SimpleData
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_view_simple.view.*

class SimpleAdapter(var context: Context, var list: List<SimpleData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_view_simple, viewGroup, false)
        view.layoutParams.width = ((context as MainActivity).window.decorView.width / 2.2).toInt()
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(context).load(list[position].imageId)
            .apply(bitmapTransform(RoundedCornersTransformation(15, 0, RoundedCornersTransformation.CornerType.ALL)))
            .into(holder.itemView.simpleItemImg)
        holder.itemView.simpleName.text = list[position].name

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}

//class SimpleAdapter(var context: Context, var list: List<SimpleData>) : PagerAdapter() {
//
//    var mOnItemClickListener: onItemClickListener? = null
//
//    interface onItemClickListener {
//        fun onItemClick(data: DetailData)
//    }
//
//    fun setOnItemClickListener(mOnItemClickListener: onItemClickListener) {
//        this.mOnItemClickListener = mOnItemClickListener
//    }
//    override fun isViewFromObject(v: View, any: Any): Boolean {
//        return v == any
//    }
//
//    override fun getCount(): Int {
//        return list.size
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val view = LayoutInflater.from(context).inflate(R.layout.item_view_simple, container, false)
//        Glide.with(context).load(list[position].imageId)
//            .apply(bitmapTransform(RoundedCornersTransformation(15,0,RoundedCornersTransformation.CornerType.ALL)))
//            .into(view.simpleItemImg)
//        view.simpleContainer.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
//view.simpleContainer.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
//
////        view.simpleItemImg.setImageResource(list[position].imageId)
////        Glide.with(context).load(list[position].imageId).into(view.simpleItemImg)
//
//        view.simpleName.text = list[position].name
//        container.addView(view)
//        return view    }
//
//    override fun getPageWidth(position: Int): Float {
//        return 0.47f
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
//}