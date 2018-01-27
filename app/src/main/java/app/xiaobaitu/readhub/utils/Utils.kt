package app.xiaobaitu.readhub.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import app.xiaobaitu.readhub.R
import org.threeten.bp.Duration
import org.threeten.bp.OffsetDateTime




/**
 * Created by baitu on 2018/1/15.
 * 简易工具类
 */
object Utils{

    private const val MINUTE = (60 * 1000).toLong()
    private const val HOUR = 60 * MINUTE
    private const val DAY = 24 * HOUR
    private const val WEEK = 7 * DAY
    private const val MONTH = 31 * DAY
    private const val YEAR = 12 * MONTH

    /**
     * 获取相对时间，eg：5分钟前、10小时前
     *
     * Kotlin笔记：与Java交互，@JvmStatic
     * 需要加上 @JvmStatic ，该方法才是一个静态方法，才能被 Java 当成静态方法调用。
     * 否则，需要实例化才能调用。
     */
    @JvmStatic
    fun getRelativeTimeWithNow(context: Context, offsetDateTime: OffsetDateTime):String{
        val offset = Duration.between(offsetDateTime, OffsetDateTime.now()).toMillis()
        return when {
            offset > YEAR -> String.format(context.getString(R.string.r_year), offset / YEAR)
            offset > MONTH -> String.format(context.getString(R.string.r_month), offset / MONTH)
            offset > WEEK -> String.format(context.getString(R.string.r_week), offset / WEEK)
            offset > DAY -> String.format(context.getString(R.string.r_day), offset / DAY)
            offset > HOUR -> String.format(context.getString(R.string.r_hour), offset / HOUR)
            offset > MINUTE -> String.format(context.getString(R.string.r_minute), offset / MINUTE)
            else -> String.format(context.getString(R.string.r_just_now), offset / MINUTE)
        }
    }

    /**
     * 获取版本号
     */
    @JvmStatic
    fun getVersionName(activity: Activity): String {
        val packageManager = activity.packageManager
        val packInfo = packageManager.getPackageInfo(activity.packageName, 0)
        return packInfo.versionName
    }

    /**
     * 复制内容到剪切板
     */
    @JvmStatic
    fun copyText(context: Context, text: String){
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("text_copy", text)
        clipboardManager.primaryClip = clip
        Toast.makeText(context, context.getString(R.string.copy_toast), Toast.LENGTH_SHORT).show()
    }

    /**
     * 网络是否可用
     */
    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo?.isAvailable ?: false
    }
}