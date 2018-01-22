package app.xiaobaitu.readhub.network

/**
 * Created by baitu on 2017/12/28.
 * 服务器接口
 */
object ServiceApi {
    private const val URL_BASE = "https://api.readhub.me"

    const val TOPIC = URL_BASE + "/topic"
    const val NEWS = URL_BASE + "/news"
    const val TECH_NEWS = URL_BASE + "/technews"

    object Params{
        val lastCursor: String = "lastCursor"
        val pageSize: String = "pageSize"
    }
}