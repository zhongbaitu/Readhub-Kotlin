package app.xiaobaitu.readhub.feature.news

import android.os.Bundle
import android.view.View
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseFragment
import app.xiaobaitu.readhub.feature.MainPresenter
import app.xiaobaitu.readhub.model.NewsInfo
import kotlinx.android.synthetic.main.fragment_topic.*

/**
 * Created by baitu on 18/1/1.
 * 科技动态
 */
class NewsFragment: BaseFragment(), MainPresenter.Callback<NewsInfo> {

    private val adapter: NewsAdapter by lazy { NewsAdapter() }

    private val presenter: MainPresenter by lazy { MainPresenter() }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.registerNewCallback(this)

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
        presenter.loadNewsData(MainPresenter.FIRST_CURSOR)
    }

    override fun loadMoreData() {
        val lastCursor = adapter.getLastItemData()
        presenter.loadNewsData(lastCursor.id)
    }

    override fun onLoading(loading: Boolean) {
        swipeRefreshLayout.isRefreshing = loading
    }

    override fun onDataRefresh(requestCursor: Int, data: NewsInfo) {
        if(requestCursor == MainPresenter.FIRST_CURSOR){
            adapter.refreshData(data.data)
            adapter.setLoadMoreEnable()
        }else{
            adapter.addDatas(data.data)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregisterNewsCallback(this)
    }

}