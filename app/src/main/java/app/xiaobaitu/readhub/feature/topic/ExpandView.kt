package app.xiaobaitu.readhub.feature.topic

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.model.NewsArray

/**
 * Created by baitu on 18/1/12.
 * 热门话题的item扩展view
 */
class TopicExpandView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val summaryTv:TextView
    private val itemLayout:LinearLayout

    init {
        val view:View = LayoutInflater.from(context).inflate(R.layout.view_topic_expand, this)
        summaryTv = view.findViewById(R.id.summaryTv)
        itemLayout = view.findViewById(R.id.itemLayout)
    }

    fun addItems(datas: List<NewsArray>) {
        itemLayout.removeAllViews()
        createChildItems(datas)
        invalidate()
    }

    fun setSummary(summary:String){
        summaryTv.text = summary
    }

    fun getItemCount():Int{
        return itemLayout.childCount
    }

    private fun createChildItems(datas: List<NewsArray>) {
        val params = LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        for(data in datas){
            val itemView: View = LayoutInflater.from(context).inflate(R.layout.view_expend_item, this, false)
            val title:TextView = itemView.findViewById(R.id.title)
            val publisher:TextView = itemView.findViewById(R.id.publisher)
            title.text = data.title
            publisher.text = data.siteName
            itemLayout.addView(itemView, params)

            itemView.setOnClickListener({

            })
        }
    }
}