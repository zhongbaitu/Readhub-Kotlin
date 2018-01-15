package app.xiaobaitu.readhub.base.activity

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.utils.ActivityLauncher
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Created by baitu on 18/1/14.
 * WebViewActivity
 */
class WebViewActivity : AppCompatActivity(){

    val TAG = "WebViewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        initWebView()

        val url = intent.getStringExtra(ActivityLauncher.EXTRA_DATA)
        if(!TextUtils.isEmpty(url)){
            loadUrl(url)
        }else{
            throw IllegalAccessError("Url can not be empty!")
        }
    }

    private fun initWebView(){
        webview.settings.javaScriptEnabled = true
        webview.settings.domStorageEnabled = true
        webview.settings.pluginState = WebSettings.PluginState.ON;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.settings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE;
        }
        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }
        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress
            }
        }
    }

    fun loadUrl(url:String){
        webview.loadUrl(url)
    }
}