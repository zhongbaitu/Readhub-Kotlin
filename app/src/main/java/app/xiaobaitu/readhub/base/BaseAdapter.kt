package app.xiaobaitu.readhub.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.xiaobaitu.readhub.R

/**
 * Created by baitu on 18/1/12.
 * BaseAdapter
 */
abstract class BaseAdapter<TData> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList: MutableList<TData> = mutableListOf()
    private var loadMoreEnable = false

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_FOOT = 1

    var itemClickListener: ((data: TData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> createMainViewHolder(parent)
            VIEW_TYPE_FOOT -> FootHolder(LayoutInflater.from(parent?.context).inflate(R.layout.view_foot, parent, false))
            else -> throw IllegalAccessError("can not found viewType!")
        }
    }

    abstract fun createMainViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder

    abstract fun bindMainViewHolder(holder: RecyclerView.ViewHolder?, position: Int)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            bindMainViewHolder(holder, position)
        }
    }

    override fun getItemCount(): Int {
        /**
         * Kotlin笔记：when 控制流 替代 if
         * when 只能替代switch ？ ，还可以替代 if
         * 详见：http://www.kotlincn.net/docs/reference/control-flow.html#when-%E8%A1%A8%E8%BE%BE%E5%BC%8F
         */
        return when {
            loadMoreEnable -> dataList.size + 1
            else -> dataList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (loadMoreEnable) {
            if (position == dataList.size) {
                VIEW_TYPE_FOOT
            } else {
                VIEW_TYPE_ITEM
            }
        } else {
            VIEW_TYPE_ITEM
        }
    }

    fun refreshData(datas: List<TData>) {
        dataList.clear()
        dataList.addAll(datas)
        notifyDataSetChanged()
    }

    fun addDatas(datas: List<TData>) {
        dataList.addAll(datas)
        notifyDataSetChanged()
    }

    fun getData(): List<TData> {
        return dataList
    }

    fun getData(position: Int): TData {
        return dataList[position]
    }

    fun setLoadMoreEnable() {
        loadMoreEnable = true
    }

    fun isLoadMoreEnable(): Boolean {
        return loadMoreEnable
    }

    fun getLastItemData(): TData {
        return dataList[dataList.size - 1]
    }

    class FootHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    fun setOnItemClickListener(listener:(data:TData) -> Unit){
        itemClickListener = listener
    }
}