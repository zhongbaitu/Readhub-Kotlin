package app.xiaobaitu.readhub.network.httpEngine

import okhttp3.*
import java.io.IOException

/**
 * Created by baitu on 2017/12/28.
 * Http请求框架
 */
object RhHttp {

    val DEFAULT_TIMEOUT: Long = 30

    private var mOkHttpClient: OkHttpClient = OkHttpClient.Builder().build()

    fun post(): RhRequest {
        return RhRequest(Method.POST)
    }

    fun get(): RhRequest {
        return RhRequest(Method.GET)
    }

    fun execute(request: Request, callback: HttpCallback) {
        callback.onBefore()
        val call = mOkHttpClient.newCall(request)
        call.enqueue(object : Callback{
            override fun onResponse(call: Call?, response: Response?) {
                if (response?.isSuccessful!!) {
                    val result: String = response.body()?.string() ?: ""
                    callback.onSuccess()
                    callback.onFinish()
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                callback.onError()
                callback.onFinish()
            }
        })
    }
}