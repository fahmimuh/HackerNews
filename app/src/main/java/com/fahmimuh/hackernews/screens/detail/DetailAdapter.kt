package com.fahmimuh.hackernews.screens.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fahmimuh.hackernews.R
import com.fahmimuh.hackernews.data.network.models.CommentResponse
import kotlinx.android.synthetic.main.comment_item.view.*

class DetailAdapter (private val commentResponse: List<CommentResponse>
): RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent,false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int = commentResponse.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.binditem(commentResponse[position])
    }

}

class CommentViewHolder (view: View): RecyclerView.ViewHolder(view){
    fun binditem(commentResponse: CommentResponse){
        itemView.txtComment.text = commentResponse.text
        itemView.txtCreator.text = commentResponse.by
    }

}
