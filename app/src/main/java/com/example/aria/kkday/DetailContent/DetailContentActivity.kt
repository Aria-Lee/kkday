package com.example.aria.kkday.DetailContent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aria.kkday.Data
import com.example.aria.kkday.R
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.detail_content_layout.*
import kotlinx.android.synthetic.main.dot.*


class DetailContentActivity : AppCompatActivity() {

    lateinit var data: Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_content_layout)

        data = Data(this)

        toolbar.inflateMenu(R.menu.detail_content_toolbar_menu)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "【2019 京都賞櫻必備】嵐山小火車保證有位限量車票（季節限定）"
        setAppBarLayout()
        initView()
        initViewPage()
    }

    lateinit var onPageChangeListener: ViewPager.OnPageChangeListener

    fun initView() {
        Glide.with(this).load(resources.getIdentifier("photo", "drawable", packageName))
            .apply(RequestOptions.bitmapTransform(CropCircleTransformation()))
            .into(photo)
        detailContentRecyclerView.adapter = DetailRecyclerViewAdapter(this, data.getDetailDataList())
        detailContentRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        LinearSnapHelper().attachToRecyclerView(detailContentRecyclerView)
    }

    fun initViewPage() {
        detailContentViewPager.adapter = DetailContentViewpagerAdapter(this, data.getDetailContentImgList())
        dot3.isEnabled = false
        onPageChangeListener = object : ViewPager.OnPageChangeListener {

            var lastPosition = 0
            var mainDotList = mutableListOf(dot3, dot4, dot5, dot6, dot7)
            var currentDotPositionInList = 0


            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(position: Int) {
                when (position > lastPosition) {
                    true -> {
                        if (currentDotPositionInList < 4) {
                            mainDotList[currentDotPositionInList].isEnabled = true
                            currentDotPositionInList += 1
                            mainDotList[currentDotPositionInList].isEnabled = false
                        }
                        when (position) {
                            5 -> dot2.visibility = View.VISIBLE
                            6 -> dot1.visibility = View.VISIBLE
                            7 -> dot9.visibility = View.INVISIBLE
                            8 -> dot8.visibility = View.INVISIBLE
                        }
                    }
                    else -> {
                        if (currentDotPositionInList > 0) {
                            mainDotList[currentDotPositionInList].isEnabled = true
                            currentDotPositionInList -= 1
                            mainDotList[currentDotPositionInList].isEnabled = false
                        }
                        when (position) {
                            3 -> dot8.visibility = View.VISIBLE
                            2 -> dot9.visibility = View.VISIBLE
                            1 -> dot1.visibility = View.INVISIBLE
                            0 -> dot2.visibility = View.INVISIBLE
                        }
                    }
                }

                lastPosition = position
            }

        }

        detailContentViewPager.addOnPageChangeListener(onPageChangeListener)
    }

    fun setAppBarLayout() {

        var appBarOffsetChangeListener = object : AppBarLayout.OnOffsetChangedListener {
            var lastVerticalOffset = 0
            var toolBarType = 0

            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                println("**********************${verticalOffset}    ${collapsingView.height}\"     ${toolbar.height}")
                if (toolBarType == 0 && lastVerticalOffset < verticalOffset && verticalOffset < -(collapsingView.height - toolbar.height)) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(this@DetailContentActivity, R.color.colorPrimary))
                    toolBarType = 1
                }

                if (toolBarType == 1 && lastVerticalOffset < verticalOffset && verticalOffset > -(collapsingView.height - toolbar.height)) {
                    toolbar.setBackgroundResource(R.drawable.toolbar_background)
                    toolBarType = 0
                }
                lastVerticalOffset = verticalOffset
            }

        }
        appBarLayout.addOnOffsetChangedListener(appBarOffsetChangeListener)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_content_toolbar_menu, menu)
//        menu!!.findItem(R.id.favorite).setOnMenuItemClickListener {
//            it.setChecked(!it.isChecked)
//             true
//        }
        return true
    }

    var isFavorite = false

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.favorite -> {
                isFavorite = !isFavorite
                if (isFavorite) item.setIcon(R.drawable.favorite)
                else item.setIcon(R.drawable.favorite_outline)
            }
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }
}
