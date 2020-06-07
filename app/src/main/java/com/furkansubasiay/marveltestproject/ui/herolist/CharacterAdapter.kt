package com.furkansubasiay.marveltestproject.ui.herolist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.furkansubasiay.marveltestproject.R
import com.furkansubasiay.marveltestproject.model.character.MarvelCharacterItem




class CharacterAdapter(private var marvelCharacters: List<MarvelCharacterItem>, val listener: OnItemClickListener) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onClick(marvelCharacter: MarvelCharacterItem)
    }
    /*init {
        mOnClickListener = View.OnClickListener { v ->
            //val item = v.tag as DummyItem

            //mListener?.onListFragmentInteraction(item)
        }
    }*/
    fun setList(marvelCharacters1: List<MarvelCharacterItem>)
    {
        this.marvelCharacters =marvelCharacters1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setFadeAnimation(holder.itemView);
        val item = marvelCharacters[position]
        holder.mName.text = item.name

        Glide.with(holder.mImage.context)
            .load(String.format("%s%s%s",item.thumbnail.path,".",item.thumbnail.extension))
            .into(holder.mImage)
        holder.click(item, listener, position)

    }

    fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 500
        view.startAnimation(anim)
    }

    override fun getItemCount(): Int = marvelCharacters.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mName: TextView = itemView.findViewById<TextView>(R.id.txt_name)
        val mImage:ImageView = itemView.findViewById(R.id.img_character)
        fun click(marvelCharacter: MarvelCharacterItem, listener: OnItemClickListener, position: Int) {
            itemView.setOnClickListener {
                listener.onClick(marvelCharacter)

            }
        }
    }
}
