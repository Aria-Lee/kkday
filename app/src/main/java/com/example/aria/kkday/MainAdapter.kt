package com.example.aria.kkday

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.HORIZONTAL
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_view_detail.view.*
import kotlinx.android.synthetic.main.main_recyclerview_item_detail.view.*
import kotlinx.android.synthetic.main.main_recyclerview_item_simple.view.*
import org.w3c.dom.Text

class MainAdapter(
    var context: Context,
    var titleList: List<String>,
    var simpleList: List<SimpleData>,
    var detailList: List<DetailData>,
    var recentList: List<DetailData>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    companion object {
        var RECENT = 0
        var SIMPLE = 1
        var SPRING = 2
        var RECOMMEND = 3
    }

    var mOnItemClickListener : onItemClickListener? = null

    interface onItemClickListener{
        fun onItemClick()
    }

    fun setOnItemClickListener(mOnItemClickListener: onItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): MainAdapter.ViewHolder {
        var view: View
        when (type) {
            SIMPLE -> {
                view = LayoutInflater.from(context).inflate(R.layout.main_recyclerview_item_simple, viewGroup, false)
                return SimpleViewHolder(context, view)
            }

            else -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_view_detail, viewGroup, false)
                return DetailViewHolder(context, view, mOnItemClickListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        var adapter =
            when (position) {
                RECENT -> DetailAdapter(context, recentList)


                SIMPLE -> SimpleAdapter(context, simpleList)


                SPRING -> DetailAdapter(context, detailList.takeLast(4))

                RECOMMEND -> DetailAdapter(context, detailList.take(4))

                else -> DetailAdapter(context, detailList.take(4))
            }

        holder.bind(titleList[position], adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
    }

    class DetailViewHolder(context: Context, itemView: View, var mOnItemClickListener: onItemClickListener?) : ViewHolder(context, itemView) {
        override fun bind(title: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            titleView = itemView.titleTextDetail
            recyclerView = itemView.itemRecyclerViewDetail
            itemView.cardView.setOnClickListener {
                mOnItemClickListener!!.onItemClick()
            }
            super.bind(title, adapter)
        }
    }

    class SimpleViewHolder(context: Context, itemView: View) : ViewHolder(context, itemView) {
        override fun bind(title: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            titleView = itemView.titleTextSimple
            recyclerView = itemView.itemRecyclerViewSimple
            super.bind(title, adapter)
        }
    }

    open class ViewHolder(var context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var titleView: TextView
        lateinit var recyclerView: RecyclerView
        open fun bind(title: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            titleView.text = title
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        }
    }
}