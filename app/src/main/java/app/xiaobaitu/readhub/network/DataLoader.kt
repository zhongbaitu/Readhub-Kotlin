package app.xiaobaitu.readhub.network

import app.xiaobaitu.readhub.model.NewsInfo
import app.xiaobaitu.readhub.model.TechNewsInfo
import app.xiaobaitu.readhub.model.TopicInfo
import app.xiaobaitu.readhub.network.httpEngine.HttpCallback
import app.xiaobaitu.readhub.network.httpEngine.RhHttp

/**
 * Created by baitu on 2017/12/28.
 * 加载网络数据的二次封装
 */
object DataLoader {

    /**
     * 加载热门话题数据
     */
    fun loadTopic(lastCursor : Long, pageSize: Int, callback: HttpCallback.SimHttpCallback<TopicInfo>.()->Unit) {
        val ca = HttpCallback.SimHttpCallback(TopicInfo::class.java)
        ca.callback()
        RhHttp.get()
                .url(ServiceApi.TOPIC)
                .addParam(ServiceApi.Params.lastCursor, lastCursor.toString())
                .addParam(ServiceApi.Params.pageSize, pageSize.toString())
                .execute(ca)
    }

    /**
     * 加载科技动态数据
     */
    fun loadNews(lastCursor : Long, pageSize: Int, callback: HttpCallback.SimHttpCallback<NewsInfo>.()->Unit) {
        val ca = HttpCallback.SimHttpCallback(NewsInfo::class.java)
        ca.callback()
        RhHttp.get()
                .url(ServiceApi.NEWS)
                .addParam(ServiceApi.Params.lastCursor, lastCursor.toString())
                .addParam(ServiceApi.Params.pageSize, pageSize.toString())
                .execute(ca)
    }

    /**
     * 加载开发者咨询数据
     */
    fun loadTechNews(lastCursor : Long, pageSize: Int, callback: HttpCallback.SimHttpCallback<TechNewsInfo>.()->Unit) {
        val ca = HttpCallback.SimHttpCallback(TechNewsInfo::class.java)
        ca.callback()
        RhHttp.get()
                .url(ServiceApi.TECH_NEWS)
                .addParam(ServiceApi.Params.lastCursor, lastCursor.toString())
                .addParam(ServiceApi.Params.pageSize, pageSize.toString())
                .execute(ca)
    }
}