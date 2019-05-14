package com.ishir.recyclerviewgallery

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val mPics = intArrayOf(R.mipmap.albert01, R.mipmap.albert02, R.mipmap.albert03, R.mipmap.albert04, R.mipmap.albert03, R.mipmap.albert02, R.mipmap.albert01, R.mipmap.albert04)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val mRecyclerView = findViewById<RecyclerView>(R.id.rv)
        //Gallery效果的LinearLayoutManager
        val galleryManager = GalleryManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView.layoutManager = galleryManager
        mRecyclerView.adapter = GalleryAdapter(this, mPics)
        //控制RecyclerView的滑动
        PagerSnapHelper().attachToRecyclerView(mRecyclerView)
        //平滑移动到Position = 1
        mRecyclerView.smoothScrollToPosition(1)
    }
}
