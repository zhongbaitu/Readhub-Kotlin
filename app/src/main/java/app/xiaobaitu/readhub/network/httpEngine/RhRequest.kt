package app.xiaobaitu.readhub.network.httpEngine

import android.net.Uri
import okhttp3.FormBody
import okhttp3.Request

/**
 * Created by baitu on 18/1/1.
 *
 */
class RhRequest(method: Method) {
    private val mParams: Map<String, String> = HashMap()
    private lateinit var mRequestUrl: String
    private val mMethod = method

    fun url(url: String): RhRequest {
        mRequestUrl = url
        return this
    }

    fun addParam(key:String, value:String): RhRequest {
        mParams.plus(Pair(key, value))
        return this
    }

    fun execute(callback: HttpCallback){
        RhHttp.execute(generateReq(), callback)
    }

    private fun generateReq():Request{
        return when (mMethod){
            Method.GET -> genetateGetReq()
            Method.POST -> generatePostReq()
        }
    }

    private fun genetateGetReq():Request{
        val requestBuilder = Request.Builder()
        val urlBuilder: Uri.Builder = Uri.parse(mRequestUrl).buildUpon()
        for (param in mParams){
            urlBuilder.appendQueryParameter(param.key, param.value)
        }
        return requestBuilder.url(urlBuilder.build().toString()).get().build()
    }

    private fun generatePostReq():Request{
        val requestBuilder = Request.Builder()
        val formBuilder = FormBody.Builder()
        for (param in mParams){
            formBuilder.add(param.key, param.value)
        }
        return requestBuilder.url(mRequestUrl).post(formBuilder.build()).build()
    }
}