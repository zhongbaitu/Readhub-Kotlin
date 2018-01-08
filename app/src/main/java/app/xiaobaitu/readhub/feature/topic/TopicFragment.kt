package app.xiaobaitu.readhub.feature.topic

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseFragment
import app.xiaobaitu.readhub.feature.MainPresenter
import app.xiaobaitu.readhub.model.TopicInfo
import kotlinx.android.synthetic.main.fragment_topic.*

/**
 * Created by baitu on 18/1/1.
 * 热门话题
 */
class TopicFragment : BaseFragment(), MainPresenter.Callback<TopicInfo> {

    private val TAG: String = "TopicFragment"

    private lateinit var adapter: TopicAdapter

    private val presenter: MainPresenter by lazy { MainPresenter() }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.register(this)

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
        presenter.loadTopicData(MainPresenter.Params.FIRST_CURSOR)
    }

    fun loadMoreData() {
        val lastCursor = adapter.getLastItemData()
        presenter.loadTopicData(lastCursor.order)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_topic
    }

    override fun onLoading(loading: Boolean) {
        swipeRefreshLayout.isRefreshing = loading
    }

    override fun onDataRefresh(requestCursor: Int, data: TopicInfo) {
        if(requestCursor == MainPresenter.FIRST_CURSOR){
            adapter.refreshData(data.data)
            adapter.setLoadMoreEnable()
        }else{
            adapter.addDatas(data.data)
        }
    }

    override fun onDestroy() {
        presenter.unregister(this)
        super.onDestroy()
    }
}