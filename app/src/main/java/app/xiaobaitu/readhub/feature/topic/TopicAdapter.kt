package app.xiaobaitu.readhub.feature.topic

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseAdapter
import app.xiaobaitu.readhub.model.Data

/**
 * Created by baitu on 18/1/6.
 *
 */
class TopicAdapter : BaseAdapter<Data>() {

    val TAG: String = "TopicAdapter"

    override fun createMainViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return TopicAdapter.TopicHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_topic, parent, false))
    }

    override fun bindMainViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as TopicAdapter.TopicHolder).setInfo(getData(position))
    }

    class TopicHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var titleTv: TextView = itemView?.findViewById(R.id.titleTv)!!
        var timeTv: TextView = itemView?.findViewById(R.id.timeTv)!!

        fun setInfo(info: Data) {
            titleTv.text = info.title
            timeTv.text = info.publishDate
        }
    }
}