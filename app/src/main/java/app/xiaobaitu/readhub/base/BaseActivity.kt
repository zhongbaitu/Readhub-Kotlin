package app.xiaobaitu.readhub.base

import android.support.v7.app.AppCompatActivity

/**
 * Created by baitu on 18/1/20.
 *
 * Kotlin笔记：open 关键字
 * 在Kotlin中，默认的类都是final，都是不可继承，这和Java相反。
 * 用open关键字修饰，才可被继承。
 * 若已被 abstract 修饰，即可不用open修饰。如：BaseFragment
 * 详见：http://www.kotlincn.net/docs/reference/classes.html#%E7%BB%A7%E6%89%BF
 */
open class BaseActivity: AppCompatActivity()