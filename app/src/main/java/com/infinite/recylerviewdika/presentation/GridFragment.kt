package com.infinite.recylerviewdika.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.infinite.recylerviewdika.Hero
import com.infinite.recylerviewdika.R
import com.infinite.recylerviewdika.databinding.FragmentGridBinding
import com.infinite.recylerviewdika.grid.GridHeroAdapter

class GridFragment : Fragment() {
    private var title = "Grid"

    private lateinit var binding: FragmentGridBinding
    private val list = ArrayList<Hero>()


    companion object {
        private const val STATE_TITLE = "state_String"
        private const val STATE_LIST = "state_list"
        private const val TITLE = "Grid"
    }

    override fun onResume() {
        super.onResume()
        activity?.title = TITLE
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGridBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHeroes.setHasFixedSize(true)

        if (savedInstanceState == null) {
            list.addAll(getListHeroes())
        } else {
            title = savedInstanceState.getString(STATE_TITLE).toString()
            val stateList = savedInstanceState.getParcelableArrayList<Hero>(STATE_LIST)

            if (stateList != null) {
                list.addAll(stateList)
            }
        }

        list.addAll(getListHeroes())
        showRecyclerGrid()
    }


        private fun showRecyclerGrid() {
        binding.rvHeroes.layoutManager = GridLayoutManager(requireActivity(), 2)
        val gridHeroAdapter = GridHeroAdapter(list)
        binding.rvHeroes.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object : GridHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listHero = ArrayList<Hero>()
        for (position in dataName.indices) {
            val hero = Hero(
                dataName[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listHero.add(hero)
        }
        return listHero
    }


    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(requireActivity(), "Kamu memilih ${hero.name}", Toast.LENGTH_SHORT).show()
    }
}