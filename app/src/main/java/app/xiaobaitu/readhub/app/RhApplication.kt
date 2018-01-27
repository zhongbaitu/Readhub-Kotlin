package app.xiaobaitu.readhub.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

/**
 * Created by baitu on 2018/1/15.
 */
class RhApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        AndroidThreeTen.init(this)
    }

    companion object {

        private lateinit var instance:RhApplication

        fun get(): RhApplication{
            return instance
        }
    }
}