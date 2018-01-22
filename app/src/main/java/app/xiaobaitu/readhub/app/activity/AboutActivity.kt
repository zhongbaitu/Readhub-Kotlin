package app.xiaobaitu.readhub.app.activity

import android.os.Bundle
import android.view.View
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseActivity
import app.xiaobaitu.readhub.network.WebUrl
import app.xiaobaitu.readhub.utils.ActivityLauncher
import app.xiaobaitu.readhub.utils.Utils
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Created by baitu on 18/1/20.
 * About page
 */
class AboutActivity: BaseActivity(), View.OnClickListener, View.OnLongClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        toolBar.setNavigationOnClickListener {
            finish()
        }

        versionTv.text = String.format(getString(R.string.version_name), Utils.getVersionName(this))

        readhubTv.text = String.format(getString(R.string.data_from), WebUrl.URL_READHUB)
        readhubTv.setOnClickListener(this)
        readhubTv.setOnLongClickListener(this)

        githubTv.text = String.format(getString(R.string.github_project), WebUrl.URL_GITHUB)
        githubTv.setOnClickListener(this)
        githubTv.setOnLongClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.readhubTv -> ActivityLauncher.launchWebView(this, WebUrl.URL_READHUB)
            R.id.githubTv -> ActivityLauncher.launchWebView(this, WebUrl.URL_GITHUB)
        }
    }

    override fun onLongClick(view: View?): Boolean {
        when(view?.id){
            R.id.readhubTv -> {
                Utils.copyText(this, WebUrl.URL_READHUB)
                return true
            }
            R.id.githubTv -> {
                Utils.copyText(this, WebUrl.URL_GITHUB)
                return true
            }
        }
        return false
    }

}