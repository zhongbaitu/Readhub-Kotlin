package app.xiaobaitu.readhub.feature.technews

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseAdapter
import app.xiaobaitu.readhub.model.TechNewsInfo

/**
 * Created by baitu on 2018/1/15.
 */
class TechNewsAdapter : BaseAdapter<TechNewsInfo.Data>() {
    override fun createMainViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return TechNewsHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_tech_news, parent, false))
    }

    override fun bindMainViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as TechNewsHolder).setInfo(getData(position))
    }

    class TechNewsHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private var titleTv: TextView = itemView?.findViewById(R.id.titleTv)!!

        fun setInfo(info: TechNewsInfo.Data){
            titleTv.text = info.title
        }
    }
}