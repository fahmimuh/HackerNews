package com.fahmimuh.hackernews.data.network.services

import com.fahmimuh.hackernews.data.network.models.CommentResponse
import com.fahmimuh.hackernews.data.network.models.StoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("topstories.json?print=pretty")
    fun getTopStories(): Call<List<Int>>

    @GET("item/{id}.json?print=pretty")
    fun getStory(@Path("id")itemId: Int): Call<StoryResponse>

    @GET("item/{id}.json?print=pretty")
    fun getComment(@Path("id")itemId: Int): Call<CommentResponse>
}