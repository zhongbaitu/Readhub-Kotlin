package app.xiaobaitu.readhub.feature.topic

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.model.Data

/**
 * Created by baitu on 18/1/6.
 *
 */
class TopicAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TAG: String = "TopicAdapter"

    private val dataList: MutableList<Data> = mutableListOf()
    private var loadMoreEnable: Boolean = false

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_FOOT = 1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            TopicHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_topic, parent, false))
        } else if (viewType == VIEW_TYPE_FOOT) {
            FootHolder(LayoutInflater.from(parent?.context).inflate(R.layout.view_foot, parent, false))
        } else {
            throw IllegalAccessError("can not found viewType!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(getItemViewType(position) == VIEW_TYPE_ITEM){
            (holder as TopicHolder).setInfo(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return if (loadMoreEnable) {
            dataList.size + 1
        } else {
            dataList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(loadMoreEnable){
            if (position == dataList.size) {
                VIEW_TYPE_FOOT
            } else {
                VIEW_TYPE_ITEM
            }
        }else{
            VIEW_TYPE_ITEM
        }
    }

    fun refreshData(datas: List<Data>) {
        dataList.clear()
        dataList.addAll(datas)
        notifyDataSetChanged()
    }

    fun addDatas(datas: List<Data>){
        for(data in datas){
            dataList.add(data)
        }
//        dataList.addAll(datas)
        notifyDataSetChanged()
    }

    fun setLoadMoreEnable() {
        loadMoreEnable = true
    }

    fun isLoadMoreEnable():Boolean{
        return loadMoreEnable
    }

    fun getLastItemData() : Data{
        return dataList[dataList.size - 1]
    }

    class TopicHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var titleTv: TextView = itemView?.findViewById(R.id.titleTv)!!
        var timeTv: TextView = itemView?.findViewById(R.id.timeTv)!!

        fun setInfo(info: Data) {
            titleTv.text = info.title
            timeTv.text = info.publishDate
        }
    }

    class FootHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}