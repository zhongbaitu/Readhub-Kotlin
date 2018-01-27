package app.xiaobaitu.readhub.feature.topic

import android.os.Bundle
import android.view.View
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseFragment
import app.xiaobaitu.readhub.app.listener.LoadDataListener
import app.xiaobaitu.readhub.feature.MainPresenter
import app.xiaobaitu.readhub.model.TopicInfo
import app.xiaobaitu.readhub.utils.MsgHelp
import kotlinx.android.synthetic.main.fragment_topic.*

/**
 * Created by baitu on 18/1/1.
 * 热门话题
 */
class TopicFragment : BaseFragment(), MainPresenter.Callback<TopicInfo>, LoadDataListener {

    private val TAG = "TopicFragment"

    /**
     * Kotlin笔记：lazy 属性
     * lazy 属性，延迟初始化，属性委托的一种。只会被初始化一次。
     * 默认情况下，对于 lazy 属性的求值是同步锁的（synchronized），这里的 adapter 只会在主线程被操作，
     * 所以可以使用 LazyThreadSafetyMode.NONE 来去掉锁，节省开销。
     * 详见：http://www.kotlincn.net/docs/reference/delegated-properties.html
     */
    private val adapter: TopicAdapter by lazy(LazyThreadSafetyMode.NONE) { TopicAdapter() }

    private val presenter: MainPresenter by lazy(LazyThreadSafetyMode.NONE) { MainPresenter() }

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
        val lastData = adapter.getLastItemData()
        presenter.loadTopicData(lastData.order)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_topic
    }

    override fun onLoading(loading: Boolean) {
        swipeRefreshLayout.isRefreshing = loading
    }

    override fun onDataRefresh(requestCursor: Long, data: TopicInfo) {
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

    override fun onDestroy() {
        presenter.unregisterTopicCallback()
        super.onDestroy()
    }
}