package app.xiaobaitu.readhub.app.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toolbar
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseActivity
import app.xiaobaitu.readhub.utils.ActivityLauncher
import app.xiaobaitu.readhub.utils.Utils
import kotlinx.android.synthetic.main.activity_webview.*


/**
 * Created by baitu on 18/1/14.
 * WebView page
 */
class WebViewActivity : BaseActivity(){

    val TAG = "WebViewActivity"

    private val toolBarMenuItemClickListener = Toolbar.OnMenuItemClickListener{ menuItem ->
        when (menuItem.itemId){
            R.id.navigation_share -> {
                share()
                return@OnMenuItemClickListener true
            }
            R.id.navigation_mark -> {
                //TODO save with database
                return@OnMenuItemClickListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        toolBar.inflateMenu(R.menu.menu_webview)
        toolBar.setOnMenuItemClickListener(toolBarMenuItemClickListener)
        toolBar.setNavigationOnClickListener {
            finish()
        }
        toolBar.setOnLongClickListener {
            Utils.copyText(this, webview.url)
            return@setOnLongClickListener true
        }

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

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                toolBar.title = title
            }
        }
    }

    fun loadUrl(url:String){
        webview.loadUrl(url)
    }

    private fun share(){
        var shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        val content: String  = toolBar.title.toString() + "\n" + webview.url + "\n" + getString(R.string.share_from)
        shareIntent.putExtra(Intent.EXTRA_TEXT, content)
        shareIntent = Intent.createChooser(shareIntent, getString(R.string.share))
        startActivity(shareIntent)
    }
}