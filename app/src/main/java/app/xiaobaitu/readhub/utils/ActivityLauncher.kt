package app.xiaobaitu.readhub.utils

import android.content.Context
import android.content.Intent
import app.xiaobaitu.readhub.base.activity.WebViewActivity

/**
 * Created by baitu on 18/1/14.
 */
object ActivityLauncher {

    const val EXTRA_DATA = "EXTRA_DATA"

    fun launchWebView(context:Context, url:String){
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(EXTRA_DATA, url)
        context.startActivity(intent)
    }
}