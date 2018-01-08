package app.xiaobaitu.readhub.feature

import android.util.Log
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
            onError { exception -> Log.e(TAG, "exception:", exception) }
        }
    }

    fun register(callback: Callback<TopicInfo>) {
        topicCallback = callback
    }

    fun unregister(callback: Callback<TopicInfo>) {
        topicCallback = null
    }

    interface Callback<T> {
        fun onLoading(loading: Boolean)
        fun onDataRefresh(requestCursor: Int, data: T)
    }
}