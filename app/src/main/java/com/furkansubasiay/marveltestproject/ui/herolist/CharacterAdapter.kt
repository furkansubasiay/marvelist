package com.furkansubasiay.marveltestproject.ui.herolist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import com.furkansubasiay.marveltestproject.BR
import com.furkansubasiay.marveltestproject.R
import com.furkansubasiay.marveltestproject.model.character.MarvelCharacterItem
import com.furkansubasiay.marveltestproject.util.ItemClickListener


class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    private lateinit var marvelCharacters: MutableList<MarvelCharacterItem>
    private var listener: ItemClickListener<MarvelCharacterItem>? = null

    constructor() : super()

    constructor(list: MutableList<MarvelCharacterItem>, listener: ItemClickListener<MarvelCharacterItem>) : super() {
        this.marvelCharacters = list
        this.listener = listener
    }

    fun setList(marvelCharacters1: MutableList<MarvelCharacterItem>)
    {
        marvelCharacters =marvelCharacters1
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(viewGroup.context), R.layout.character_item, viewGroup, false
        )
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setFadeAnimation(holder.itemView);
        if (getItem(position) != null) {
            val item = getItem(position)
            holder.binding.setVariable(BR.viewModel, item)

            if (listener != null)
                holder.binding.setVariable(BR.clickListener, listener)

            holder.binding.executePendingBindings()
        }

    }

    fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 500
        view.startAnimation(anim)
    }

    override fun getItemCount(): Int {
        return if(::marvelCharacters.isInitialized) marvelCharacters.size else 0
    }

    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    fun getItem(index: Int): MarvelCharacterItem? {
        return if (index < marvelCharacters.size) marvelCharacters[index] else null
    }

}
