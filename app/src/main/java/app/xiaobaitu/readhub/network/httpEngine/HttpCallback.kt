package app.xiaobaitu.readhub.network.httpEngine

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import app.xiaobaitu.readhub.utils.MsgHelp
import com.google.gson.Gson
import okhttp3.Response


/**
 * Created by baitu on 18/1/1.
 * http请求回调封装
 */
interface HttpCallback {
    fun onBefore()
    fun onSuccess(response: Response)
    fun onError(type:Int, exception: Exception)
    fun onFinish()

    class SimHttpCallback<out DataBean>(private val clazz: Class<DataBean>) : HttpCallback {

        private var successCallback: ((data: DataBean) -> Unit)? = null
        private var errorCallback: ((type:Int, exception: Exception) -> Unit)? = null
        private var beforeCallback: (() -> Unit)? = null
        private var finishCallback: (() -> Unit)? = null

        private val mainHandler : Handler = Handler(Looper.getMainLooper())

        override fun onBefore() {
            mainHandler.post({
                beforeCallback?.invoke()
            })
        }

        override fun onSuccess(response: Response) {
            val re : String? = response.body()?.string()
            if(!TextUtils.isEmpty(re)){
                val data : DataBean = Gson().fromJson<DataBean>(re, clazz)
                mainHandler.post({
                    successCallback?.invoke(data)
                })
            } else {
                onError(MsgHelp.TYPE_UNKNOW, RuntimeException("Response's body can not be empty."))
            }
        }

        override fun onError(type:Int, exception: Exception) {
            mainHandler.post({
                errorCallback?.invoke(type, exception)
            })
        }

        override fun onFinish() {
            mainHandler.post({
                finishCallback?.invoke()
            })
        }

        fun onBefore(listener: () -> Unit) {
            beforeCallback = listener
        }

        fun onSuccess(listener: (data: DataBean) -> Unit) {
            successCallback = listener
        }

        fun onError(listener: (type:Int, exception: Exception) -> Unit) {
            errorCallback = listener
        }

        fun onFinish(listener: () -> Unit) {
            finishCallback = listener
        }
    }
}