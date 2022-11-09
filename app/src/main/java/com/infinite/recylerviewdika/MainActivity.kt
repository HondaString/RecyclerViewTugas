package com.infinite.recylerviewdika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.infinite.recylerviewdika.databinding.ActivityMainBinding
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_list, R.id.navigation_grid, R.id.navigation_card
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_setting -> startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            R.id.action_fragment -> {
                val viewPager = findViewById<ViewPager2>(R.id.view_pager)
                viewPager.visibility = View.GONE
                binding.tabs.visibility = View.GONE
                binding.navView.visibility = View.VISIBLE
                binding.navFragment.navHostFragment.visibility = View.VISIBLE
                Toast.makeText(this,"Fragment Mode", Toast.LENGTH_SHORT).show()
            }
            R.id.action_viewPager -> {
                binding.navFragment.navHostFragment.visibility = View.GONE
                binding.viewPager.visibility = View.VISIBLE
                binding.tabs.visibility = View.VISIBLE
                binding.navView.visibility = View.GONE
                Toast.makeText(this,"ViewPager Mode", Toast.LENGTH_SHORT).show()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}