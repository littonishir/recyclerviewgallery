package com.ishir.recyclerviewgallery

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class GalleryManager : LinearLayoutManager {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)

    //最大缩小比例，原始都是1，也就是最多缩小为原来view的0.8，这个可以自己改
    private var smaller = 0f

    //水平滚动的时候这里会执行
    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State?): Int {
        handleHorizontalView()
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    private fun handleView() {
        when (orientation) {
            HORIZONTAL -> {
                handleHorizontalView()
            }
            VERTICAL -> {
                handleVerticalView()
            }
        }
    }

    private var decorationNormal = 1f / 15
    //垂直滚动的时候会走这里
    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        handleVerticalView()
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    private fun handleHorizontalView() {
        //view居中显示的时候left位置
        val centerViewLeft = width * 2 * decorationNormal
        //从一个位置移动到另一个位置item移动的距离
        val moveX = width * (1 - 4 * decorationNormal)
        calculateScale(centerViewLeft, moveX)
    }

    private fun handleVerticalView() {
        //view居中显示的时候top位置
        val centerViewTop = height * 2 * decorationNormal
        //从一个位置移动到另一个位置item移动的距离
        val moveY = height * (1 - 4 * decorationNormal)
        calculateScale(centerViewTop, moveY)
    }

    /**
     * @param centerViewLeft 水平滑动的时候是left位置，垂直滑动是top位置
     * @param moveDistance 水平滑动是x轴移动距离，垂直滑动是y轴移动距离
     * */
    private fun calculateScale(centerViewLeft: Float, moveDistance: Float) {
        repeat(childCount) {
            getChildAt(it)?.apply {
                var left = this.left
                when (orientation) {
                    VERTICAL -> {
                        left = this.top
                    }
                }
                var factor = (left - centerViewLeft) / moveDistance
                factor = Math.max(-1f, factor)
                factor = Math.min(1f, factor)
                if (factor > 0) {
                    //屏幕右边的view往中心滑动
                    scale(this, 1f - factor * smaller)
                } else {
                    //屏幕中间的view往左边滑动
                    scale(this, 1f + factor * smaller)
                }
            }
        }
    }

    private fun scale(view: View, scale: Float) {
        view.apply {
            pivotX = this.width / 2f
            pivotY = this.height / 2f
            scaleX = scale
            scaleY = scale
        }
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        //因为布局首次加载完不滑动是不会执行scroll方法的，所以这里得修改view的scale
        if (smaller == 0f) {
            smaller = 2 * decorationNormal / (1 - 4 * decorationNormal)
        }
        handleView()
    }
}