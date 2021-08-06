package com.walker.war

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.qmuiteam.qmui.arch.QMUIFragmentActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.walker.war.adapter.ViewPagerAdapter
import com.walker.war.databinding.ActivityMainBinding
import com.walker.war.di.qualifier.Test2
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

@AndroidEntryPoint
class MainActivity : QMUIFragmentActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d("guowtest", "main url=" + url.hashCode())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        supportActionBar?.title = "123"
        supportActionBar?.height
        binding.vpView.adapter = ViewPagerAdapter(this)
        binding.vpView.offscreenPageLimit = 1
        binding.vpView.isUserInputEnabled = false
        navView.setOnNavigationItemSelectedListener {
            Log.d("guowtest", "menutime=" + it.itemId)
            when (it.itemId) {
                R.id.navigation_home -> binding.vpView.setCurrentItem(0)
                R.id.navigation_dashboard -> binding.vpView.setCurrentItem(1)
                R.id.navigation_notifications -> binding.vpView.setCurrentItem(2)
            }
            return@setOnNavigationItemSelectedListener true
        }

        binding.vpView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                navView.selectedItemId = when (position) {
                    0 -> R.id.navigation_home
                    1 -> R.id.navigation_dashboard
                    2 -> R.id.navigation_notifications
                    else -> R.id.navigation_home
                }

            }
        })

    }
}