package com.fahmimuh.hackernews.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fahmimuh.hackernews.R
import com.fahmimuh.hackernews.data.network.models.StoryResponse
import kotlinx.android.synthetic.main.story_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainAdapter (private val storyResponse: List<StoryResponse>,
                   private  val listener: (StoryResponse) -> Unit
) :RecyclerView.Adapter<StoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.story_item, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bindItem(storyResponse[position], listener)
    }

    override fun getItemCount(): Int = storyResponse.size
}

class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(storyResponse: StoryResponse, listener: (StoryResponse) -> Unit){
        itemView.txtTitle.text = storyResponse.title
        itemView.txtCreator.text = storyResponse.by
        itemView.txtScore.text = storyResponse.score.toString()
        if (storyResponse.kids != null){
            itemView.txtComment.text = storyResponse.kids.size.toString()
        }else itemView.txtComment.text = "0"



        itemView.onClick { listener(storyResponse) }
    }
}
