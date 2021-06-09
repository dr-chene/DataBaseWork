package com.viper.databasework.view.activity

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.viper.databasework.R
import com.viper.databasework.base.BaseActivity
import com.viper.databasework.databinding.ActivityMainBinding
import com.viper.databasework.view.fragment.HomeFragment
import com.viper.databasework.view.fragment.MineFragment
import com.viper.databasework.view.fragment.ShopFragment
import com.viper.databasework.view.fragment.TestFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onInitView() {
        binding.mainViewPager.apply {
            isUserInputEnabled = false
            adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun getItemCount() = 3

                override fun createFragment(position: Int): Fragment {
                    return when (position) {
                        0 -> HomeFragment()
                        1 -> MineFragment()
                        else -> TestFragment()
                    }
                }
            }
        }
    }

    override fun onInitAction() {
        binding.mainNavBottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_bottom_home -> nav(0)
                R.id.nav_bottom_mine -> nav(1)
//                R.id.nav_bottom_test -> nav(2)
                else -> false
            }
        }
    }

    override fun getContentViewResId() = R.layout.activity_main

    private fun nav(position: Int): Boolean {
        binding.mainViewPager.setCurrentItem(position, false)
        return true
    }
}