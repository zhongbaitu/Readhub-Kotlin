package app.xiaobaitu.readhub.feature

import android.util.Log
import app.xiaobaitu.readhub.model.NewsInfo
import app.xiaobaitu.readhub.model.TopicInfo
import app.xiaobaitu.readhub.network.DataLoader

/**
 * Created by baitu on 18/1/8.
 * 主界面Presenter
 */
class MainPresenter {

    companion object Params{
        const val FIRST_CURSOR = -1
        const val PAGE_SIZE = 20
    }

    private val TAG: String = "MainPresenter"

    private var isTopicLoading = false
    private var topicCallback: Callback<TopicInfo>? = null

    private var isNewsLoading = false
    private var newsCallback: Callback<NewsInfo>? = null

    /**
     * 加载热门话题数据
     */
    fun loadTopicData(lastCursor: Int) {
        if (isTopicLoading) {
            return
        }
        isTopicLoading = true
        DataLoader.loadTopic(lastCursor, PAGE_SIZE) {
            onBefore {
                if (lastCursor == FIRST_CURSOR) {
                    topicCallback?.onLoading(true)
                }
            }
            onSuccess { data ->
                topicCallback?.onDataRefresh(lastCursor, data)
            }
            onFinish {
                topicCallback?.onLoading(false)
                isTopicLoading = false
            }
            onError { exception -> Log.e(TAG, "loadTopicData exception:", exception) }
        }
    }

    /**
     * 加载科技动态数据
     */
    fun loadNewsData(lastCursor: Int) {
        if (isNewsLoading) {
            return
        }
        isNewsLoading = true
        DataLoader.loadNews(lastCursor, PAGE_SIZE) {
            onBefore {
                if (lastCursor == FIRST_CURSOR) {
                    newsCallback?.onLoading(true)
                }
            }
            onSuccess { data ->
                newsCallback?.onDataRefresh(lastCursor, data)
            }
            onFinish {
                newsCallback?.onLoading(false)
                isNewsLoading = false
            }
            onError { exception -> Log.e(TAG, "loadNewsData exception:", exception) }
        }
    }

    fun registerTopicCallback(callback: Callback<TopicInfo>) {
        topicCallback = callback
    }

    fun unregisterTopicCallback(callback: Callback<TopicInfo>) {
        topicCallback = null
    }

    fun registerNewCallback(callback: Callback<NewsInfo>) {
        newsCallback = callback
    }

    fun unregisterNewsCallback(callback: Callback<NewsInfo>) {
        newsCallback = null
    }

    interface Callback<T> {
        fun onLoading(loading: Boolean)
        fun onDataRefresh(requestCursor: Int, data: T)
    }
}