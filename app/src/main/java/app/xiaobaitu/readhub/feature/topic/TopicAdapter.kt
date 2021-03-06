package app.xiaobaitu.readhub.feature.topic

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseAdapter
import app.xiaobaitu.readhub.model.TopicInfo
import app.xiaobaitu.readhub.utils.Utils
import org.threeten.bp.OffsetDateTime

/**
 * Created by baitu on 18/1/6.
 *
 */
class TopicAdapter : BaseAdapter<TopicInfo.TopicData>() {

    val TAG = "TopicAdapter"

    override fun createMainViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return TopicHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_topic, parent, false))
    }

    override fun bindMainViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as TopicAdapter.TopicHolder).setInfo(getData(position))
    }

    /**
     * Kotlin笔记：嵌套类与内部类
     * TopicHolder 是一个嵌套类，默认和Java的静态内部类一样，不会持有外部类的引用。
     * 若被 inner 修饰，则会持有外部类的引用，可以调用外部类的成员。
     * 详见：http://www.kotlincn.net/docs/reference/nested-classes.html
     */
    private class TopicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var titleTv: TextView = itemView.findViewById(R.id.titleTv)
        private var timeTv: TextView = itemView.findViewById(R.id.timeTv)
        private var topicExpandView: TopicExpandView = itemView.findViewById(R.id.topicExpandView)

        fun setInfo(info: TopicInfo.TopicData) {
            titleTv.text = info.title
            timeTv.text = Utils.getRelativeTimeWithNow(itemView.context, OffsetDateTime.parse(info.publishDate))

            topicExpandView.removeItems()
            topicExpandView.visibility = View.GONE
            itemView.setOnClickListener({
                if(topicExpandView.getItemCount() > 0){
                    if(topicExpandView.visibility == View.VISIBLE){
                        topicExpandView.visibility = View.GONE
                    }else{
                        topicExpandView.visibility = View.VISIBLE
                    }
                }else{
                    topicExpandView.setSummary(info.summary)
                    topicExpandView.addItems(info.newsArray)
                    topicExpandView.visibility = View.VISIBLE
                }
            })
        }
    }
}