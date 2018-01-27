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

    private val layoutId:Int

    init {
        layoutId = this.getLayoutId()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutId, container, false)
    }

    protected abstract fun getLayoutId():Int
}