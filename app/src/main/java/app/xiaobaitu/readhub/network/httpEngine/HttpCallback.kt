package app.xiaobaitu.readhub.network.httpEngine

/**
 * Created by baitu on 18/1/1.
 */
interface HttpCallback{
    fun onBefore()
    fun onSuccess()
    fun onError()
    fun onFinish()

    class SimHttpCallback:HttpCallback{

        private var successCallback : (() -> Unit)? = null
        private var errorCallback : (() -> Unit)? = null
        private var beforeCallback : (() -> Unit)? = null
        private var finishCallback : (() -> Unit)? = null

        override fun onBefore() {
            beforeCallback?.invoke()
        }

        override fun onSuccess() {
            successCallback?.invoke()
        }

        override fun onError() {
            errorCallback?.invoke()
        }

        override fun onFinish() {
            finishCallback?.invoke()
        }

        fun before(listener: () -> Unit) {
            beforeCallback = listener
        }

        fun success(listener: () -> Unit) {
            successCallback = listener
        }

        fun error(listener: () -> Unit) {
            errorCallback = listener
        }

        fun finish(listener: () -> Unit) {
            finishCallback = listener
        }
    }
}