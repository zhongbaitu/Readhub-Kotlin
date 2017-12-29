package app.xiaobaitu.readhub.network

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by baitu on 2017/12/28.
 */
class RetrofitClient private constructor(context: Context, url: String) {

    val DEFAULT_TIMEOUT: Long = 30

    private var mOkHttpClient: OkHttpClient = OkHttpClient.Builder().build()


    companion object {
        var client: RetrofitClient? = null

        fun getIntance(context: Context, url: String): RetrofitClient {
            if (client == null) {
                synchronized(RetrofitClient::class) {
                    if (client == null) {
                        client = RetrofitClient(context, url)
                    }
                }
            }
            return client!!
        }
    }

    fun test() {
        Thread(Runnable {
            val request: Request = Request.Builder().get().url("https://api.readhub.me/topic?lastCursor=1&pageSize=0").build()
            val call = mOkHttpClient.newCall(request)
            val response = call.execute()
            if (response.isSuccessful) {
                val result: String = response.body()?.string() ?: "";
                Log.d("RetrofitClient", "success:"+result)
            }else{
                Log.d("RetrofitClient", "肥佬~~")
            }
        }).start()

    }

}