package com.fahmimuh.hackernews.screens.main

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmimuh.hackernews.data.network.models.StoryResponse
import com.fahmimuh.hackernews.data.network.services.ApiRepository
import com.fahmimuh.hackernews.screens.detail.DetailActivity
import com.fahmimuh.hackernews.util.invisible
import com.fahmimuh.hackernews.util.visible
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivityForResult


class MainActivity : AppCompatActivity(), MainView {
    private var storyResponses: MutableList<StoryResponse> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.fahmimuh.hackernews.R.layout.activity_main)

        val request = ApiRepository

        adapter = MainAdapter(storyResponses) {
            startActivityForResult<DetailActivity>(0, "detail" to it)
        }

        presenter = MainPresenter(this, request)

        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = adapter

        presenter.getTopStoriesId()



    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showStory(data: List<Int>?) {
        if (data != null) {
            for(i in 0 until 10){
                presenter.getStory(i, data[i])
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressBar.setProgress((i+1)*10, true)
                }
            }
        }
    }

    override fun showStoryList(data: List<StoryResponse>?) {
        if (data != null) {
            storyResponses.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0){
            if (resultCode == Activity.RESULT_OK) {
                val favoriteData = data?.getStringExtra("title")
                txtFavorite.text = favoriteData

            }
        }
    }


}
