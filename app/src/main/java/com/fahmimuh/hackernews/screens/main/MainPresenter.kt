package com.fahmimuh.hackernews.screens.main

import android.util.Log
import com.fahmimuh.hackernews.data.network.models.StoryResponse
import com.fahmimuh.hackernews.data.network.services.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(
        private val view: MainView,
        private val apiRepository: ApiRepository) {

    fun getTopStoriesId(){
        val storyServices =  apiRepository.create()
        storyServices.getTopStories().enqueue(object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                if (response.isSuccessful) {
                    val topStories: List<Int>? = response.body()
                    view.showStory(topStories)
                }
                view.showLoading()
            }

            override fun onFailure(call: Call<List<Int>>, error: Throwable) {
                Log.e("retrofitFailure", "error ${error.message}")
            }
        })
    }

    fun getStory(i: Int, storyId: Int){
        val storyServices =  apiRepository.create()
        storyServices.getStory(storyId).enqueue(object : Callback<StoryResponse> {
            override fun onResponse(call: Call<StoryResponse>, response: Response<StoryResponse>) {
                Log.d("cekData", response.body()!!.title)
                if (response.isSuccessful){
                    val storyResponses: List<StoryResponse> = listOf(response.body() as StoryResponse)
                    view.showStoryList(storyResponses)
                }

                view.hideLoading()
            }

            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                Log.e("retrofitFailure", "error ${t.message}")
            }

        })
    }
}