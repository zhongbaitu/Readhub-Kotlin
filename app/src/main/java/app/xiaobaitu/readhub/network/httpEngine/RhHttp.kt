package app.xiaobaitu.readhub.network.httpEngine

import okhttp3.*
import java.io.IOException

/**
 * Created by baitu on 2017/12/28.
 * Http请求框架
 */
object RhHttp {

    val TAG = "RhHttp"

    val DEFAULT_TIMEOUT = 30

    private var mOkHttpClient = OkHttpClient.Builder().build()

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
                if (response?.isSuccessful == true) {
                    callback.onSuccess(response)
                    callback.onFinish()
                }else{
                    callback.onError(RuntimeException("Response was failure."))
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                callback.onError(e?: RuntimeException("Response was failure and IOException was null."))
                callback.onFinish()
            }
        })
    }
}