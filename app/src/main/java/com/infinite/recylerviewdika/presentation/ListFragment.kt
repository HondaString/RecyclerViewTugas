package com.infinite.recylerviewdika.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.infinite.recylerviewdika.Hero
import com.infinite.recylerviewdika.R
import com.infinite.recylerviewdika.databinding.FragmentListBinding
import com.infinite.recylerviewdika.list.ListHeroAdapter

class ListFragment : Fragment() {
    private var title = "List"

    private lateinit var binding: FragmentListBinding
    private val list = ArrayList<Hero>()


    companion object {
        private const val STATE_LIST = "state_list"
        private const val TITLE = "List"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list.addAll(getListHeroes())
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
        if (savedInstanceState == null) {
        } else {
            val stateList = savedInstanceState.getParcelableArrayList<Hero>(STATE_LIST)

            if (stateList != null) {
                list.addAll(stateList)
            }
        }
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHeroes.setHasFixedSize(true)
// untuk ganti orientation

            showRecyclerList()
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


    private fun showRecyclerList() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(requireActivity())
        val listHeroAdapter = ListHeroAdapter(list)
        binding.rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(hero: Hero) {
                showSelectedHero(hero)
            }
        })
    }

    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(requireActivity(), "Kamu memilih ${hero.name}", Toast.LENGTH_SHORT).show()
    }
}