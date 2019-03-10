package com.example.aria.kkday

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber



class MainActivity : AppCompatActivity() {

    var recentList = mutableListOf<DetailData>()
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }

        var data = Data(this)
        var a = 5


//        searchView.clearFocus()
//        window.decorView.requestFocus()
        adapter = MainAdapter(this, data.getTitleList(), data.getSimpleDataList(), data.getDetailDataList(), recentList, ::intentToDetailContent)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun intentToDetailContent(){
        var intent = Intent(this, DetailContentActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        adapter.notifyDataSetChanged()
        super.onResume()
    }

}

class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

    }


}