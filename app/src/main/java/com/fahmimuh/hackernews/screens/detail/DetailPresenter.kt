package com.fahmimuh.hackernews.screens.detail

import android.util.Log
import com.fahmimuh.hackernews.data.network.models.CommentResponse
import com.fahmimuh.hackernews.data.network.services.ApiRepository
import retrofit2.Call
import retrofit2.Response

class DetailPresenter(
        private val view: DetailView,
        private val apiRepository: ApiRepository) {

    fun getComment(commentId: Int) {
        val storyServices = apiRepository.create()
        storyServices.getComment(commentId).enqueue(object : retrofit2.Callback<CommentResponse> {
            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                if (response.isSuccessful) {
//                    Log.d("cekKomen", response.body().by)
                    val commentResponse: List<CommentResponse> = listOf(response.body() as CommentResponse)
                    view.showComment(commentResponse)
                }
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                Log.e("retrofitFailure", "error ${t.message}")
            }


        })
    }
}