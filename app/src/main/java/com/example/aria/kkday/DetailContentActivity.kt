package com.example.aria.kkday

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_content_layout.*
import kotlinx.android.synthetic.main.detail_content_layout.view.*
import kotlinx.android.synthetic.main.dot.*
import kotlinx.android.synthetic.main.item_view_detail.*
import timber.log.Timber
import java.net.URL
import java.text.FieldPosition


class DetailContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_content_layout)

        var data = Data(this)

        toolbar.inflateMenu(R.menu.detail_content_toolbar_menu)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        detailContentViewPager.adapter = DetailContentViewpagerAdapter(this, data.getDetailContentImgList())
        Glide.with(this).load(resources.getIdentifier("photo", "drawable", packageName))
            .apply(RequestOptions.bitmapTransform(CropCircleTransformation()))
            .into(photo)
        detailContentRecyclerView.adapter = DetailRecyclerViewAdapter(this, data.getDetailDataList())
        detailContentRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        LinearSnapHelper().attachToRecyclerView(detailContentRecyclerView)
        initViewPage()
        detailContentViewPager.addOnPageChangeListener(onPageChangeListener)

    }

    lateinit var onPageChangeListener: ViewPager.OnPageChangeListener

    fun initViewPage() {
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
                if(isFavorite) item.setIcon(R.drawable.favorite)
                else item.setIcon(R.drawable.favorite_outline)
            }
                android.R.id.home -> {
                    onBackPressed()
                }
            }
            return true
        }
    }
