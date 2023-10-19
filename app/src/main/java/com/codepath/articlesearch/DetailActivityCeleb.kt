package com.codepath.articlesearch

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivityCeleb"

class DetailActivityCeleb : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var popularityTextView: TextView
    private lateinit var knownForTextView: TextView
    private lateinit var kfTitleTextView: TextView
    private lateinit var kfSummTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.celebactivity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.celebmediaImage)
        titleTextView = findViewById(R.id.celebmediaTitle)
        popularityTextView = findViewById(R.id.celebmediaAbstract)
        knownForTextView = findViewById(R.id.celebmediaByline)
        kfTitleTextView = findViewById(R.id.celebknownForTitle)
        kfSummTextView = findViewById(R.id.knownForDesc)

        // TODO: Get the extra from the Intent
        val celebrity = intent.getSerializableExtra(C_EXTRA) as Celebrity

        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = celebrity.name
        popularityTextView.text = celebrity.popularity
        knownForTextView.text = celebrity.knownFor


        if (!celebrity.knownformovies.isNullOrEmpty()) {
            val firstKnownForMovie = celebrity.knownformovies[0]
            kfTitleTextView.text = firstKnownForMovie.title
            kfSummTextView.text = firstKnownForMovie.overview
        }


        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + celebrity.imageUrl)
            .into(mediaImageView)
    }
}