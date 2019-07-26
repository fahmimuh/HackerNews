package com.fahmimuh.hackernews.screens.main

import com.fahmimuh.hackernews.data.network.models.StoryResponse

interface MainView {
    fun showStory(data: List<Int>?)
    fun showStoryList(data: List<StoryResponse>?)
    fun showLoading()
    fun hideLoading()

}