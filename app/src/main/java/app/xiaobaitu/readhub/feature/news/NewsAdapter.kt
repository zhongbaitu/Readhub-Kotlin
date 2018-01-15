package app.xiaobaitu.readhub.feature.news

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseAdapter
import app.xiaobaitu.readhub.model.NewsInfo
import app.xiaobaitu.readhub.utils.Utils
import org.threeten.bp.OffsetDateTime

/**
 * Created by baitu on 2018/1/15.
 */
class NewsAdapter : BaseAdapter<NewsInfo.Data>() {
    override fun createMainViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return NewsHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_news, parent, false))
    }

    override fun bindMainViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as NewsHolder).setInfo(getData(position))
    }

    class NewsHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private var titleTv: TextView = itemView?.findViewById(R.id.titleTv)!!
        private var summaryTv: TextView = itemView?.findViewById(R.id.summaryTv)!!
        private var timeTv: TextView = itemView?.findViewById(R.id.timeTv)!!

        fun setInfo(info:NewsInfo.Data){
            titleTv.text = info.title
            summaryTv.text = info.summaryAuto
            timeTv.text = String.format(
                    itemView.resources.getString(R.string.format_site_time),
                    info.siteName,
                    Utils.getRelativeTimeWithNow(itemView.context, OffsetDateTime.parse(info.publishDate)))
        }
    }
}