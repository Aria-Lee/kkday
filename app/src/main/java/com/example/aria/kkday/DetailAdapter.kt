package com.example.aria.kkday

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_detail.view.*

//class DetailAdapter(var context: Context, var list: List<DetailData>) :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): ViewHolder {
//        var view = LayoutInflater.from(context).inflate(R.layout.item_view_detail, viewGroup, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
////        holder.bind(list[position])
//        holder.itemView.detailItemImg.setImageResource(list[position].imageId)
//        holder.itemView.name.text = list[position].name
//        holder.itemView.location.text = list[position].location
//        holder.itemView.rating.rating = list[position].rating.toFloat()
//        holder.itemView.ratingCount.text = list[position].ratingCount
//        holder.itemView.count.text = list[position].count
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
////        fun bind(data: DetailData){
////            itemView.detailItemImg.setImageResource(data.imageId)
////            itemView.name.text = data.name
////            itemView.location.text = data.location
////            itemView.rating.rating = data.rating.toFloat()
////            itemView.ratingCount.text = data.ratingCount
////            itemView.count.text = data.count
////        }
//    }
//}

class DetailAdapter(var context: Context, var list: List<DetailData>) : PagerAdapter() {

    var mOnItemClickListener: onItemClickListener? = null

    interface onItemClickListener {
        fun onItemClick(data: DetailData)
    }

    fun setOnItemClickListener(mOnItemClickListener: onItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener
    }
    override fun isViewFromObject(v: View, any: Any): Boolean {
        return v == any
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view_detail, container, false)
//        view.detailItemImg.setImageResource(list[position].imageId)
        Glide.with(context).load(list[position].imageId).into(view.detailItemImg)


        view.name.text = list[position].name
        view.location.text = list[position].location
        view.rating.rating = list[position].rating.toFloat()
        view.ratingCount.text = list[position].ratingCount
        view.count.text = list[position].count
        view.setOnClickListener {
            mOnItemClickListener!!.onItemClick(list[position])
        }
        container.addView(view)
        return view    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}