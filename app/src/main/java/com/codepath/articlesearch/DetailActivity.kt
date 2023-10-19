package com.codepath.articlesearch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var rDateTextView: TextView
    private lateinit var summaryTextView: TextView
    private lateinit var voteTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        rDateTextView = findViewById(R.id.mediaAbstract)
        summaryTextView = findViewById(R.id.mediaByline)
        voteTextView = findViewById(R.id.mediaVote)

        // TODO: Get the extra from the Intent
       val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = movie.title
        summaryTextView.text = movie.summary
        rDateTextView.text = movie.releaseDate
        voteTextView.text = movie.vote

        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.imageUrl)
            .into(mediaImageView)
    }
}