package com.infinite.recylerviewdika.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.infinite.recylerviewdika.Hero
import com.infinite.recylerviewdika.databinding.ItemGridHeroBinding

class GridHeroAdapter(private val listHero: ArrayList<Hero>) :
    RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): GridHeroAdapter.GridViewHolder {
        val binding =
            ItemGridHeroBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size

    // untuk saat ini wajib menggunkan inner ketika menggunakan class didalam class
    inner class GridViewHolder(private val binding: ItemGridHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(hero.photo)
                    .apply(RequestOptions().override(550, 550))
                    .into(imgItemPhoto)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(hero) }
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }

}