package app.xiaobaitu.readhub.network

import app.xiaobaitu.readhub.network.httpEngine.HttpCallback
import app.xiaobaitu.readhub.network.httpEngine.RhHttp
import app.xiaobaitu.readhub.network.httpEngine.ServiceApi

/**
 * Created by baitu on 2017/12/28.
 * 加载网络数据的二次封装
 */
object DataLoader {

    fun loadTopic(callback: HttpCallback.SimHttpCallback.()->Unit) {
        val ca = HttpCallback.SimHttpCallback()
        ca.callback()
        RhHttp.get()
                .url(ServiceApi.TOPIC)
                .addParam(ServiceApi.Params.lastCursor, "-1")
                .addParam(ServiceApi.Params.pageSize, "20")
                .execute(ca)
    }
}