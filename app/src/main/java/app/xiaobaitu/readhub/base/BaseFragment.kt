package app.xiaobaitu.readhub.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by baitu on 18/1/1.
 * Fragment基类
 */
abstract class BaseFragment:Fragment() {

    private val mLayoutId:Int

    init {
        mLayoutId = this.getLayoutId()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(mLayoutId, container, false)
    }

    protected abstract fun getLayoutId():Int

    protected abstract fun loadData()

    protected abstract fun loadMoreData()
}