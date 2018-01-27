package app.xiaobaitu.readhub.feature.technews

import android.os.Bundle
import android.view.View
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseFragment
import app.xiaobaitu.readhub.app.listener.LoadDataListener
import app.xiaobaitu.readhub.feature.MainPresenter
import app.xiaobaitu.readhub.model.TechNewsInfo
import app.xiaobaitu.readhub.utils.ActivityLauncher
import app.xiaobaitu.readhub.utils.MsgHelp
import kotlinx.android.synthetic.main.fragment_topic.*
import org.threeten.bp.OffsetDateTime

/**
 * Created by baitu on 18/1/1.
 * 开发者咨询
 */
class TechNewsFragment: BaseFragment(), MainPresenter.Callback<TechNewsInfo>, LoadDataListener {

    private val adapter: TechNewsAdapter by lazy(LazyThreadSafetyMode.NONE) { TechNewsAdapter() }

    private val presenter: MainPresenter by lazy(LazyThreadSafetyMode.NONE) { MainPresenter() }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.registerTechNewCallback(this)

        adapter.setOnItemClickListener {
            data -> ActivityLauncher.launchWebView(context, data.mobileUrl)
        }

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
        val lastData = adapter.getLastItemData()
        val time = OffsetDateTime.parse(lastData.publishDate)
        presenter.loadTechNewsData(time.toInstant().toEpochMilli())
    }

    override fun onLoading(loading: Boolean) {
        swipeRefreshLayout.isRefreshing = loading
    }

    override fun onDataRefresh(requestCursor: Long, data: TechNewsInfo) {
        if(requestCursor == MainPresenter.FIRST_CURSOR){
            adapter.refreshData(data.data)
            adapter.setLoadMoreEnable()
        }else{
            adapter.addDatas(data.data)
        }
    }

    override fun onDataLoadError(type: Int) {
        MsgHelp.error(context, type)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tech_news
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregisterTechNewCallback()
    }

}