package app.xiaobaitu.readhub.feature

import android.util.Log
import app.xiaobaitu.readhub.model.NewsInfo
import app.xiaobaitu.readhub.model.TechNewsInfo
import app.xiaobaitu.readhub.model.TopicInfo
import app.xiaobaitu.readhub.network.DataLoader

/**
 * Created by baitu on 18/1/8.
 * 主界面Presenter
 */
class MainPresenter {

    companion object Params{
        const val FIRST_CURSOR:Long = -1
        const val PAGE_SIZE = 20
    }

    private val TAG: String = "MainPresenter"

    private var isTopicLoading = false
    private var topicCallback: Callback<TopicInfo>? = null

    private var isNewsLoading = false
    private var newsCallback: Callback<NewsInfo>? = null

    private var isTechNewsLoading = false
    private var techNewsCallback: Callback<TechNewsInfo>? = null

    /**
     * 加载热门话题数据
     */
    fun loadTopicData(lastCursor: Long) {
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
    fun loadNewsData(lastCursor: Long) {
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

    /**
     * 加载开发者咨询
     */
    fun loadTechNewsData(lastCursor: Long) {
        if (isTechNewsLoading) {
            return
        }
        isTechNewsLoading = true
        DataLoader.loadTechNews(lastCursor, PAGE_SIZE) {
            onBefore {
                if (lastCursor == FIRST_CURSOR) {
                    techNewsCallback?.onLoading(true)
                }
            }
            onSuccess { data ->
                techNewsCallback?.onDataRefresh(lastCursor, data)
            }
            onFinish {
                techNewsCallback?.onLoading(false)
                isTechNewsLoading = false
            }
            onError { exception -> Log.e(TAG, "loadTechNewsData exception:", exception) }
        }
    }

    fun registerTopicCallback(callback: Callback<TopicInfo>) {
        topicCallback = callback
    }

    fun unregisterTopicCallback() {
        topicCallback = null
    }

    fun registerNewCallback(callback: Callback<NewsInfo>) {
        newsCallback = callback
    }

    fun unregisterNewsCallback() {
        newsCallback = null
    }

    fun registerTechNewCallback(callback: Callback<TechNewsInfo>) {
        techNewsCallback = callback
    }

    fun unregisterTechNewCallback() {
        techNewsCallback = null
    }

    interface Callback<T> {
        fun onLoading(loading: Boolean)
        fun onDataRefresh(requestCursor: Long, data: T)
    }
}