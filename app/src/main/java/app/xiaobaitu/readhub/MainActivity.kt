package app.xiaobaitu.readhub

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import app.xiaobaitu.readhub.base.BaseFragment
import app.xiaobaitu.readhub.feature.news.NewsFragment
import app.xiaobaitu.readhub.feature.technews.TechNewsFragment
import app.xiaobaitu.readhub.feature.topic.TopicFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainActivity"

    private val PAGE_TOPIC = 0
    private val PAGE_NEWS = 1
    private val PAGE_TECH_NEWS = 2

    private val mRhPageAdapter:RhPageAdapter

    init {
        val fragmentList:List<BaseFragment> = listOf(TopicFragment(), NewsFragment(), TechNewsFragment())
        mRhPageAdapter = RhPageAdapter(supportFragmentManager, fragmentList)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchPage(PAGE_TOPIC)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchPage(PAGE_NEWS)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                switchPage(PAGE_TECH_NEWS)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mainViewPager.adapter = mRhPageAdapter
    }

    private fun switchPage(page:Int){
        mainViewPager.setCurrentItem(page, true)
    }

    class RhPageAdapter(fm: FragmentManager?, data:List<BaseFragment>) : FragmentPagerAdapter(fm) {

        private val mFragmentList: List<BaseFragment> = data

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }
    }
}
