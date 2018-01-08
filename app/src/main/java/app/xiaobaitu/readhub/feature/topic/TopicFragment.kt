package app.xiaobaitu.readhub.feature.topic

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseFragment
import app.xiaobaitu.readhub.network.DataLoader
import kotlinx.android.synthetic.main.fragment_topic.*

/**
 * Created by baitu on 18/1/1.
 * 热门话题
 */
class TopicFragment : BaseFragment() {

    private val TAG: String = "TopicFragment"

    private lateinit var adapter: TopicAdapter

    private var isLoading = false

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TopicAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1 && adapter.isLoadMoreEnable()) {
                    loadMoreData()
                }
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }

        loadData()
    }

    fun loadData() {
        if(isLoading){
            return
        }
        isLoading = true
        DataLoader.loadTopic(DataLoader.FIRST_CURSOR) {
            onSuccess { data ->
                adapter.refreshData(data.data)
                adapter.setLoadMoreEnable()
            }
            onFinish {
                swipeRefreshLayout.isRefreshing = false
                isLoading = false
            }
            onError { exception -> Log.e(TAG, "exception:", exception) }
        }
    }

    fun loadMoreData() {
        if(isLoading){
            return
        }
        isLoading = true
        val lastCursor = adapter.getLastItemData()
        DataLoader.loadTopic(lastCursor.order) {
            onSuccess { data ->
                adapter.addDatas(data.data)
            }
            onFinish { isLoading = false }
            onError { exception -> Log.e(TAG, "exception:", exception) }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_topic
    }
}