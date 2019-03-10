package com.example.aria.kkday.Main

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aria.kkday.*
import kotlinx.android.synthetic.main.main_recyclerview_item_detail.view.*
import kotlinx.android.synthetic.main.main_recyclerview_item_simple.view.*
import java.security.AccessController.getContext

class MainAdapter(
    var context: Context,
    var titleList: List<String>,
    var simpleList: List<SimpleData>,
    var detailList: List<DetailData>,
    var recentList: MutableList<DetailData>,
    var cb: () -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    companion object {
        var RECENT = 0
        var SIMPLE = 1
        var SPRING = 2
        var RECOMMEND = 3
    }

    lateinit var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): ViewHolder {
        var view: View
        when (type) {
            SIMPLE -> {
                view = LayoutInflater.from(context).inflate(R.layout.main_recyclerview_item_simple, viewGroup, false)
                return SimpleViewHolder(context, view)
            }

            else -> {
                view = LayoutInflater.from(context).inflate(R.layout.main_recyclerview_item_detail, viewGroup, false)
                return DetailViewHolder(context, view)
            }
        }
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        adapter =
            when (position) {
                RECENT -> DetailAdapter(
                    context,
                    recentList
                )

                SIMPLE -> SimpleAdapter(
                    context,
                    simpleList
                )

                SPRING -> DetailAdapter(
                    context,
                    detailList.take(4)
                )

                RECOMMEND -> DetailAdapter(
                    context,
                    detailList.takeLast(4)
                )

                else -> DetailAdapter(context, detailList.take(4))
            }

        if (position != SIMPLE) {
            (adapter as DetailAdapter).setOnItemClickListener(object :
                DetailAdapter.onItemClickListener {
                override fun onItemClick(data: DetailData) {
                    cb.invoke()
                    if (data !in recentList) {
                        recentList.add(data)
                        adapter.notifyDataSetChanged()
                    }
                }
            })
        }

        holder.bind(titleList[position], adapter)

        if (position == 0 && recentList.size == 0) {
            holder.titleView.visibility = View.GONE
        } else holder.titleView.visibility = View.VISIBLE
    }

    class DetailViewHolder(context: Context, itemView: View) : ViewHolder(context, itemView) {
        override fun bind(title: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            titleView = itemView.titleTextDetail
            recyclerView = itemView.detailRecyclerView
            if (isFirstTime) {
                recyclerView.addItemDecoration(MyItemDecoration(dpToInt(8), dpToInt(12)))
                LinearSnapHelper().attachToRecyclerView(recyclerView)
                isFirstTime = false
            }
            super.bind(title, adapter)
        }
    }

    class SimpleViewHolder(context: Context, itemView: View) : ViewHolder(context, itemView) {
        override fun bind(title: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            titleView = itemView.titleTextSimple
            recyclerView = itemView.simpleRecyclerView
            if (isFirstTime) {
                recyclerView.addItemDecoration(MyItemDecoration(dpToInt(4), dpToInt(16)))
                LeftLinearSnapHelper().attachToRecyclerView(recyclerView)
                isFirstTime = false
            }
            super.bind(title, adapter)
        }
    }

    open class ViewHolder(var context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var titleView: TextView
        lateinit var recyclerView: RecyclerView
        var isFirstTime = true
        open fun bind(title: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            titleView.text = title
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }

        fun dpToInt(dp: Int): Int{
            val scale = context.resources.displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }
    }


}

//class DetailViewHolder(context: Context, itemView: View) : ViewHolder(context, itemView) {
//    override fun bind(title: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
//        titleView = itemView.titleTextDetail
//        recyclerView = itemView.itemRecyclerViewDetail
//        super.bind(title, adapter)
//    }
//}
//
//class SimpleViewHolder(context: Context, itemView: View) : ViewHolder(context, itemView) {
//    override fun bind(title: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
//        titleView = itemView.titleTextSimple
//        recyclerView = itemView.itemRecyclerViewSimple
//        super.bind(title, adapter)
//    }
//}
//
//open class ViewHolder(var context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
//    lateinit var titleView: TextView
//    lateinit var recyclerView: RecyclerView
//    open fun bind(title: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
//        titleView.text = title
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
//    }
//}