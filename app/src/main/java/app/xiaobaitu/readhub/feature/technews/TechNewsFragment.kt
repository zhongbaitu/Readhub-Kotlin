package app.xiaobaitu.readhub.feature.technews

import android.os.Bundle
import android.view.View
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseFragment
import app.xiaobaitu.readhub.feature.MainPresenter
import app.xiaobaitu.readhub.model.TechNewsInfo
import kotlinx.android.synthetic.main.fragment_topic.*

/**
 * Created by baitu on 18/1/1.
 * 开发者咨询
 */
class TechNewsFragment: BaseFragment(), MainPresenter.Callback<TechNewsInfo> {

    private val adapter: TechNewsAdapter by lazy { TechNewsAdapter() }

    private val presenter: MainPresenter by lazy { MainPresenter() }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.registerTechNewCallback(this)

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
        presenter.loadTechNewsData(MainPresenter.FIRST_CURSOR)
    }

    override fun loadMoreData() {
        val lastCursor = adapter.getLastItemData()
        presenter.loadTechNewsData(lastCursor.id)
    }

    override fun onLoading(loading: Boolean) {
        swipeRefreshLayout.isRefreshing = loading
    }

    override fun onDataRefresh(requestCursor: Int, data: TechNewsInfo) {
        if(requestCursor == MainPresenter.FIRST_CURSOR){
            adapter.refreshData(data.data)
            adapter.setLoadMoreEnable()
        }else{
            adapter.addDatas(data.data)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tech_news
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregisterTechNewCallback(this)
    }

}