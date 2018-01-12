package app.xiaobaitu.readhub.base.view

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * Created by baitu on 18/1/12.
 */
class LoadMoreRecyclerView(context: Context?, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    private var loadMoreListener: (() -> Unit)? = null

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1) {
                    loadMoreListener?.invoke()
                }
            }
        })
    }

    fun setOnLoadMoreListener(listener: () -> Unit) {
        loadMoreListener = listener
    }
}