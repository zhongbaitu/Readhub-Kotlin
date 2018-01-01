package app.xiaobaitu.readhub

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import app.xiaobaitu.readhub.network.DataLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainActivity"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchPage()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchPage()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                switchPage()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun switchPage(){
//        mainViewPager.adapter =
    }

    fun test() {
        DataLoader.loadTopic {
            success { data ->  data.pageSize}
            error { exception -> Log.e(TAG, "exception:", exception) }
        }
    }

//    internal class RhPageAdapter : PagerAdapter() {
//
//        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//        override fun getCount(): Int {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//    }
}
