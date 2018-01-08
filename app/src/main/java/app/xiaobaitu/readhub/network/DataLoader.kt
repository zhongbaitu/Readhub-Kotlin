package app.xiaobaitu.readhub.network

import app.xiaobaitu.readhub.model.TopicInfo
import app.xiaobaitu.readhub.network.httpEngine.HttpCallback
import app.xiaobaitu.readhub.network.httpEngine.RhHttp
import app.xiaobaitu.readhub.network.httpEngine.ServiceApi

/**
 * Created by baitu on 2017/12/28.
 * 加载网络数据的二次封装
 */
object DataLoader {

    const val FIRST_CURSOR = -1

    private const val PAGE_SIZE = "20"

    fun loadTopic(lastCursor : Int, callback: HttpCallback.SimHttpCallback<TopicInfo>.()->Unit) {
        val ca = HttpCallback.SimHttpCallback(TopicInfo::class.java)
        ca.callback()
        RhHttp.get()
                .url(ServiceApi.TOPIC)
                .addParam(ServiceApi.Params.lastCursor, lastCursor.toString())
                .addParam(ServiceApi.Params.pageSize, PAGE_SIZE)
                .execute(ca)
    }
}