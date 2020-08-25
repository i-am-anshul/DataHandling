package com.anshul.datahandling.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.contains
import androidx.recyclerview.widget.RecyclerView
import com.anshul.datahandling.R
import com.anshul.datahandling.data.Monster
import com.anshul.datahandling.utilities.PrefsHelper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.monster_grid_item.view.*

class MainRecyclerAdapter(val context: Context,
                          val monsters: List<Monster>,
                          val itemListener: MonsterItemListener):

            RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){

        val nameText = itemView.findViewById<TextView>(R.id.nameText)
        val monsterImage = itemView.findViewById<ImageView>(R.id.monsterImage)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val layoutStyle = PrefsHelper.getItemType(parent.context)
        val layoutId = if (layoutStyle == "grid"){
            R.layout.monster_grid_item
        }else{
            R.layout.monster_list_item
        }

        val view = inflater.inflate(layoutId, parent, false)
        return  ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val monster = monsters[position]
        with(holder){
            nameText.let {
                it.text = monster.monsterName
                it.contentDescription = monster.monsterName
            }
            ratingBar?.rating = monster.scariness.toFloat()
            Glide.with(context)
                .load(monster.thumbnailUrl)
                .into(monsterImage)

            holder.itemView.setOnClickListener {
                itemListener.onMonserItemClick(monster)
            }

        }
    }

    override fun getItemCount() = monsters.size

    interface MonsterItemListener {
        fun onMonserItemClick(monster: Monster)
    }
}