package com.infinite.recylerviewdika.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.infinite.recylerviewdika.Hero
import com.infinite.recylerviewdika.R
import com.infinite.recylerviewdika.cardview.CardViewHeroAdapter
import com.infinite.recylerviewdika.databinding.FragmentCardBinding

class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private val list = ArrayList<Hero>()

    // untuk ganti orientation
    private var mode: Int = 0

// untuk support ganti orientation
    companion object {
        private const val STATE_LIST = "state_list"
        private const val TITLE = "Card"
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
        binding = FragmentCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvHeroes.setHasFixedSize(true)
// untuk ganti orientation
        if (savedInstanceState == null) {
            list.addAll(getListHeroes())
        } else {
            val stateList = savedInstanceState.getParcelableArrayList<Hero>(STATE_LIST)

            if (stateList != null) {
                list.addAll(stateList)
            }
        }
        list.addAll(getListHeroes())
        showRecyclerCardView()
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


    private fun showRecyclerCardView() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(requireActivity())
        val cardViewAdapter = CardViewHeroAdapter(list)
        binding.rvHeroes.adapter = cardViewAdapter
    }

}