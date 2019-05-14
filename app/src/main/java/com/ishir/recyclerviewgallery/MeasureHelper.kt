package com.ishir.recyclerviewgallery

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


class MeasureHelper {
    private var mPagePadding = 15
    private var mShowLeftCardWidth = 60

    fun onCreateViewHolder(parent: ViewGroup, itemView: View) {
        val lp = itemView.layoutParams as RecyclerView.LayoutParams
        lp.width = parent.width - dip2px(itemView.context, (2 * (mPagePadding + mShowLeftCardWidth)).toFloat())
        itemView.layoutParams = lp
    }

    fun onBindViewHolder(itemView: View, position: Int, itemCount: Int) {
        val padding = dip2px(itemView.context, mPagePadding.toFloat())
        itemView.setPadding(padding, 0, padding, 0)
        val leftMarin = if (position == 0) padding + dip2px(itemView.context, mShowLeftCardWidth.toFloat()) else 0
        val rightMarin = if (position == itemCount - 1) padding + dip2px(itemView.context, mShowLeftCardWidth.toFloat()) else 0
        setViewMargin(itemView, leftMarin, 0, rightMarin, 0)
    }

    private fun setViewMargin(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val lp = view.layoutParams as ViewGroup.MarginLayoutParams
        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
            lp.setMargins(left, top, right, bottom)
            view.layoutParams = lp
        }
    }

    fun setPagePadding(pagePadding: Int) {
        mPagePadding = pagePadding
    }

    fun setShowLeftCardWidth(showLeftCardWidth: Int) {
        mShowLeftCardWidth = showLeftCardWidth
    }

    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}


