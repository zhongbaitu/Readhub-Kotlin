package app.xiaobaitu.readhub.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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

    fun getVersionName(activity: Activity): String {
        val packageManager = activity.packageManager
        val packInfo = packageManager.getPackageInfo(activity.packageName, 0)
        return packInfo.versionName
    }

    fun copyText(context: Context, text: String){
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("text_copy", text)
        clipboardManager.primaryClip = clip
        Toast.makeText(context, context.getString(R.string.copy_toast), Toast.LENGTH_SHORT).show()
    }

}