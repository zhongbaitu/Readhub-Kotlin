package app.xiaobaitu.readhub.network.httpEngine

/**
 * Created by baitu on 2017/12/28.
 * 服务器接口
 */
object ServiceApi {
    const val BASE_URL = "https://api.readhub.me"

    const val TOPIC = BASE_URL + "/topic"
    const val NEWS = BASE_URL + "/news"
    const val TECH_NEWS = BASE_URL + "/technews"

    object Params{
        val lastCursor: String = "lastCursor"
        val pageSize: String = "pageSize"
    }
}