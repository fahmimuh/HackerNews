package com.fahmimuh.hackernews.screens.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmimuh.hackernews.data.network.models.CommentResponse
import com.fahmimuh.hackernews.data.network.models.StoryResponse
import com.fahmimuh.hackernews.data.network.services.ApiRepository
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast
import android.content.Intent
import android.app.Activity
import com.fahmimuh.hackernews.R


class DetailActivity : AppCompatActivity(), DetailView {
    private var commentResponse: MutableList<CommentResponse> = mutableListOf()
    private lateinit var presenter: DetailPresenter
    private lateinit var adapter: DetailAdapter
    private var storyDetail: StoryResponse? = null
    private var favoriteState = false

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

        storyDetail = intent.extras?.getParcelable<StoryResponse>("detail")

        txtTitle.text = storyDetail?.title
        txtCreator.text = storyDetail?.by

        if (storyDetail?.kids?.size != null) {
            for (i in 0 until storyDetail!!.kids!!.size) {
                presenter.getComment(storyDetail!!.kids!![i])
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                favoriteState = favoriteState == false
                if (favoriteState == true){
                    toast("Set to Favotire")
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showComment(data: List<CommentResponse>) {
        commentResponse.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun finish() {
        if (favoriteState == true){
            val intent = Intent()
            intent.putExtra("title", storyDetail?.title )
            setResult(Activity.RESULT_OK, intent)
        }
        super.finish()
    }
}
