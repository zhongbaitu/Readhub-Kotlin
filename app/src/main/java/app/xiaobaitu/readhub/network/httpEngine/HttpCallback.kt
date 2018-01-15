package app.xiaobaitu.readhub.network.httpEngine

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import okhttp3.Response
import java.io.IOException


/**
 * Created by baitu on 18/1/1.
 * http请求回调封装
 */
interface HttpCallback {
    fun onBefore()
    fun onSuccess(response: Response)
    fun onError(exception: IOException)
    fun onFinish()

    class SimHttpCallback<out DataBean>(private val clazz: Class<DataBean>) : HttpCallback {

        private var successCallback: ((data: DataBean) -> Unit)? = null
        private var errorCallback: ((exception: IOException) -> Unit)? = null
        private var beforeCallback: (() -> Unit)? = null
        private var finishCallback: (() -> Unit)? = null

        private val mainHandler : Handler = Handler(Looper.getMainLooper())

        override fun onBefore() {
            mainHandler.post({
                beforeCallback?.invoke()
            })
        }

        override fun onSuccess(response: Response) {
            val re : String = response.body()?.string()!!
            val data : DataBean = Gson().fromJson<DataBean>(re ?:"", clazz)
            mainHandler.post({
                successCallback?.invoke(data)
            })
        }

        override fun onError(exception: IOException) {
            mainHandler.post({
                errorCallback?.invoke(exception)
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

        fun onError(listener: (exception: IOException) -> Unit) {
            errorCallback = listener
        }

        fun onFinish(listener: () -> Unit) {
            finishCallback = listener
        }
    }
}