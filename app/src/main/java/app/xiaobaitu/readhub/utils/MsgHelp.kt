package app.xiaobaitu.readhub.utils

import android.content.Context
import android.widget.Toast
import app.xiaobaitu.readhub.R

/**
 * Created by baitu on 18/1/23.
 * 错误状态消息处理
 */
object MsgHelp{

    val TYPE_UNKNOW = -1;
    val TYPE_NO_NETWORK : Int = 0

    fun error(context: Context, type:Int){
        when(type){
            TYPE_NO_NETWORK -> Toast.makeText(context, context.getString(R.string.msg_no_network), Toast.LENGTH_SHORT).show()
        }
    }
}