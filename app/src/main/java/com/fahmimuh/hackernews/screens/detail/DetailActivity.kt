package com.fahmimuh.hackernews.screens.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmimuh.hackernews.R
import com.fahmimuh.hackernews.data.network.models.CommentResponse
import com.fahmimuh.hackernews.data.network.models.StoryResponse
import com.fahmimuh.hackernews.data.network.services.ApiRepository
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailView {
    private var commentResponse: MutableList<CommentResponse> = mutableListOf()
    private lateinit var presenter: DetailPresenter
    private lateinit var adapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Story Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository

        adapter = DetailAdapter(commentResponse)
        presenter = DetailPresenter(this, request)

        rvComment.layoutManager = LinearLayoutManager(this)
        rvComment.adapter = adapter

        val storyDetail = intent.extras?.getParcelable<StoryResponse>("detail")

        txtTitle.text = storyDetail?.title
        txtCreator.text = storyDetail?.by

        for (i in 0 until storyDetail?.kids?.size!!) {
            presenter.getComment(storyDetail.kids[i])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        var menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showComment(data: List<CommentResponse>) {
        if(data != null){
            commentResponse.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }
}
