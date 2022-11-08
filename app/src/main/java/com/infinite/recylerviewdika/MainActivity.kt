package com.infinite.recylerviewdika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.infinite.recylerviewdika.cardview.CardViewHeroAdapter
import com.infinite.recylerviewdika.databinding.ActivityMainBinding
import com.infinite.recylerviewdika.grid.GridHeroAdapter
import com.infinite.recylerviewdika.list.ListHeroAdapter

class MainActivity : AppCompatActivity() {
//    private var title = "Model List"
//
//    private lateinit var binding: ActivityMainBinding
//    private val list = ArrayList<Hero>()
//
//    // untuk ganti orientation
//    private var mode: Int = 0
//
//    // untuk support ganti orientation
//    companion object {
//        private const val STATE_TITLE = "state_String"
//        private const val STATE_LIST = "state_list"
//        private const val STATE_MODE = "state_mode"
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.rvHeroes.setHasFixedSize(true)
//
//        // untuk ganti orientation
//        if (savedInstanceState == null) {
//            setActionBarTitle(title)
//            list.addAll(getListHeroes())
//            showRecyclerList()
//            mode = R.id.action_list
//        } else {
//            title = savedInstanceState.getString(STATE_TITLE).toString()
//            val stateList = savedInstanceState.getParcelableArrayList<Hero>(STATE_LIST)
//            val stateMode = savedInstanceState.getInt(STATE_MODE)
//
//            setActionBarTitle(title)
//            if (stateList != null) {
//                list.addAll(stateList)
//            }
//            setMode(stateMode)
//        }
//
//        list.addAll(getListHeroes())
//        showRecyclerList()
//        setActionBarTitle(title)
//    }
//
//    private fun setActionBarTitle(title: String) {
//        supportActionBar?.title = title
//    }
//
//    private fun getListHeroes(): ArrayList<Hero> {
//        val dataName = resources.getStringArray(R.array.data_name)
//        val dataDescription = resources.getStringArray(R.array.data_description)
//        val dataPhoto = resources.getStringArray(R.array.data_photo)
//
//        val listHero = ArrayList<Hero>()
//        for (position in dataName.indices) {
//            val hero = Hero(
//                dataName[position],
//                dataDescription[position],
//                dataPhoto[position]
//            )
//            listHero.add(hero)
//        }
//        return listHero
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString(STATE_LIST, title)
//        outState.putParcelableArrayList(STATE_LIST, list)
//        outState.putInt(STATE_MODE, mode)
//    }
//
//    private fun showRecyclerList() {
//        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
//        val listHeroAdapter = ListHeroAdapter(list)
//        binding.rvHeroes.adapter = listHeroAdapter
//
//        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Hero) {
//                showSelectedHero(data)
//            }
//        })
//    }
//
//    // untuk grid
//    private fun showRecyclerGrid() {
//        binding.rvHeroes.layoutManager = GridLayoutManager(this, 2)
//        val gridHeroAdapter = GridHeroAdapter(list)
//        binding.rvHeroes.adapter = gridHeroAdapter
//
//        gridHeroAdapter.setOnItemClickCallback(object : GridHeroAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Hero) {
//                showSelectedHero(data)
//            }
//        })
//    }
//
//    // untuk cardview
//    private fun showRecyclerCardView() {
//        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
//        val cardViewAdapter = CardViewHeroAdapter(list)
//        binding.rvHeroes.adapter = cardViewAdapter
//    }
//
//    private fun showSelectedHero(hero: Hero) {
//        Toast.makeText(this, "Kamu memilih ${hero.name}", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        setMode(item.itemId)
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun setMode(selectedMode: Int) {
//        when (selectedMode) {
//            R.id.action_list -> {
//                title = "Mode List"
//                showRecyclerList()
//            }
//            R.id.action_grid -> {
//                title = "Mode Grid"
//                showRecyclerGrid()
//            }
//            R.id.action_cardview -> {
//                title = "Mode CardView"
//                showRecyclerCardView()
//            }
//        }
//        mode = selectedMode
//        setActionBarTitle(title)
//    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_setting -> startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
        return super.onOptionsItemSelected(item)
    }
}