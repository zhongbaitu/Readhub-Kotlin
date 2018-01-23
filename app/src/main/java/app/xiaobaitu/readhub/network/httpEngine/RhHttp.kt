package app.xiaobaitu.readhub.network.httpEngine

import android.accounts.NetworkErrorException
import app.xiaobaitu.readhub.app.RhApplication
import app.xiaobaitu.readhub.utils.MsgHelp
import app.xiaobaitu.readhub.utils.Utils
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
        if(!Utils.isNetworkAvailable(RhApplication.Instance.get())){
            callback.onError(MsgHelp.TYPE_NO_NETWORK, NetworkErrorException("no network."))
        }
        val call = mOkHttpClient.newCall(request)
        call.enqueue(object : Callback{
            override fun onResponse(call: Call?, response: Response?) {
                if (response?.isSuccessful == true) {
                    callback.onSuccess(response)
                    callback.onFinish()
                }else{
                    callback.onError(MsgHelp.TYPE_UNKNOW, RuntimeException("Response was failure."))
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                callback.onError(MsgHelp.TYPE_UNKNOW, e?: RuntimeException("Response was failure and IOException was null."))
                callback.onFinish()
            }
        })
    }
}