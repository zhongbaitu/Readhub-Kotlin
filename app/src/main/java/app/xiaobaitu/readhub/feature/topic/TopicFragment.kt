package app.xiaobaitu.readhub.feature.topic

import android.os.Bundle
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

    private val adapter: TopicAdapter by lazy { TopicAdapter() }

    private val presenter: MainPresenter by lazy { MainPresenter() }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.registerTopicCallback(this)

        loadMoreRecyclerView.adapter = adapter
        loadMoreRecyclerView.setOnLoadMoreListener({
            if(adapter.isLoadMoreEnable()){
                loadMoreData()
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }

        loadData()
    }

    override fun loadData() {
        presenter.loadTopicData(MainPresenter.Params.FIRST_CURSOR)
    }

    override fun loadMoreData() {
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
        presenter.unregisterTopicCallback(this)
        super.onDestroy()
    }
}