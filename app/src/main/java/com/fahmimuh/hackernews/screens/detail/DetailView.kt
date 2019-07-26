package com.fahmimuh.hackernews.screens.detail

import com.fahmimuh.hackernews.data.network.models.CommentResponse

interface DetailView {
    fun showComment(data: List<CommentResponse>)
}