package app.xiaobaitu.readhub.network.httpEngine

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
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

    class SimHttpCallback<T> : HttpCallback {

        private var successCallback: ((data: T) -> Unit)? = null
        private var errorCallback: ((exception: IOException) -> Unit)? = null
        private var beforeCallback: (() -> Unit)? = null
        private var finishCallback: (() -> Unit)? = null

        override fun onBefore() {
            beforeCallback?.invoke()
        }

        override fun onSuccess(response: Response) {
            val clazs = T::class
            val data = Gson().fromJson<T>(response.body()?.string() ?:"", clazs.javaObjectType)
            successCallback?.invoke(data)
        }

        override fun onError(exception: IOException) {
            errorCallback?.invoke(exception)
        }

        override fun onFinish() {
            finishCallback?.invoke()
        }

        fun before(listener: () -> Unit) {
            beforeCallback = listener
        }

        fun success(listener: (data: T) -> Unit) {
            successCallback = listener
        }

        fun error(listener: (exception: IOException) -> Unit) {
            errorCallback = listener
        }

        fun finish(listener: () -> Unit) {
            finishCallback = listener
        }
    }
}