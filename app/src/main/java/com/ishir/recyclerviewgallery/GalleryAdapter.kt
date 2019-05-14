package com.ishir.recyclerviewgallery

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

class GalleryAdapter(private val mContext: Context, private val mList: IntArray) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private val mAdapterHelper = MeasureHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        mAdapterHelper.onCreateViewHolder(parent, itemView)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mAdapterHelper.onBindViewHolder(holder.itemView, position, itemCount)
        holder.imageView.setImageResource(mList[position])
        holder.imageView.setOnClickListener { Toast.makeText(mContext, "当前条目：$position", Toast.LENGTH_SHORT).show() }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById<View>(R.id.iv) as ImageView

    }
}
