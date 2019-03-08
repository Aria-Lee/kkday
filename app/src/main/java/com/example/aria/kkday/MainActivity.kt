package com.example.aria.kkday

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber



class MainActivity : AppCompatActivity() {

    var recentList = mutableListOf<DetailData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }

        var data = Data(this)

        searchView.clearFocus()

        mainRecyclerView.adapter = MainAdapter(this, data.getTitleList(), data.getSimpleDataList(), data.getDetailDataList(), recentList)
    }
}

class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

    }
}