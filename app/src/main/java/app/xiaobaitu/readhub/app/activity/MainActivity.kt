package app.xiaobaitu.readhub.app.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.widget.Toolbar
import app.xiaobaitu.readhub.R
import app.xiaobaitu.readhub.base.BaseActivity
import app.xiaobaitu.readhub.base.BaseFragment
import app.xiaobaitu.readhub.feature.news.NewsFragment
import app.xiaobaitu.readhub.feature.technews.TechNewsFragment
import app.xiaobaitu.readhub.feature.topic.TopicFragment
import app.xiaobaitu.readhub.utils.ActivityLauncher
import kotlinx.android.synthetic.main.activity_main.*

/**
 * main page
 */
class MainActivity : BaseActivity() {

    val TAG : String = "MainActivity"

    private val PAGE_TOPIC = 0
    private val PAGE_NEWS = 1
    private val PAGE_TECH_NEWS = 2

    private val rhPageAdapter: RhPageAdapter

    init {
        val fragmentList:List<BaseFragment> = listOf(TopicFragment(), NewsFragment(), TechNewsFragment())
        rhPageAdapter = RhPageAdapter(supportFragmentManager, fragmentList)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchPage(PAGE_TOPIC)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_news -> {
                switchPage(PAGE_NEWS)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tech_news -> {
                switchPage(PAGE_TECH_NEWS)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val toolBarMenuItemClickListener = Toolbar.OnMenuItemClickListener{ menuItem ->
        when (menuItem.itemId){
            R.id.navigation_about -> {
                ActivityLauncher.launchAbout(this)
                return@OnMenuItemClickListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBar.inflateMenu(R.menu.menu_main)
        toolBar.setOnMenuItemClickListener(toolBarMenuItemClickListener)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        mainViewPager.adapter = rhPageAdapter

        mainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    PAGE_TOPIC -> navigation.selectedItemId = R.id.navigation_home
                    PAGE_NEWS -> navigation.selectedItemId = R.id.navigation_news
                    PAGE_TECH_NEWS -> navigation.selectedItemId = R.id.navigation_tech_news
                }
            }
        })
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
