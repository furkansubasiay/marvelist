package com.furkansubasiay.marveltestproject.ui.detail.comics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furkansubasiay.marveltestproject.R
import com.furkansubasiay.marveltestproject.model.comics.ComicsItem

/**
 * Created by FURKAN SUBAŞIAY on 2020-06-06.
 */

class ComicsAdapter(private val comicsList: List<ComicsItem>) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comics_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setFadeAnimation(holder.itemView);
        val item = comicsList[position]
        holder.mTitle.text = item.title

        Glide.with(holder.mImage.context)
            .load(String.format("%s%s%s",item.comics_thumbnail.path,".",item.comics_thumbnail.extension))
            .into(holder.mImage)

    }
    fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 500
        view.startAnimation(anim)
    }
    override fun getItemCount(): Int = comicsList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitle: TextView = itemView.findViewById<TextView>(R.id.txt_comics_title)
        val mImage: ImageView = itemView.findViewById(R.id.img_comics)
    }
}
