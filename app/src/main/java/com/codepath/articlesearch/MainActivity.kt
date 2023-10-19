package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val MOVIE_SEARCH_URL =
    "https://api.themoviedb.org/3/discover/movie?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&page=1&start_date=2023-01-01"
private const val CELEB_URL = "https://api.themoviedb.org/3/trending/person/day?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<Movie>()
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    private lateinit var celebrityRecyclerView: RecyclerView
    private val celebrities = mutableListOf<Celebrity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // Set up moviesRecyclerView
        moviesRecyclerView = binding.movies
        val layoutManagerMovies = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        moviesRecyclerView.layoutManager = layoutManagerMovies
        val movieAdapter = MovieAdapter(this, movies)
        moviesRecyclerView.adapter = movieAdapter

        // Set up celebrityRecyclerView
        celebrityRecyclerView = binding.celebrities
        val layoutManagerCelebrities = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        celebrityRecyclerView.layoutManager = layoutManagerCelebrities
        val celebrityAdapter = CelebrityAdapter(this, celebrities)
        celebrityRecyclerView.adapter = celebrityAdapter

        val client = AsyncHttpClient()
        client.get(MOVIE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched articles: $json")
                try {
                    // TODO: Create the parsedJSON
                    val parsedJson = createJson().decodeFromString(
                        SearchNewResultsMovie.serializer(),
                        json.jsonObject.toString()
                    )


                    // TODO: Do something with the returned json (contains article information)
                    parsedJson.results?.let { list ->
                        movies.addAll(list)
                    }

                    // TODO: Save the articles and reload the screen
                    parsedJson.results?.let { list ->
                        movies.addAll(list)

                        // Reload the screen
                        movieAdapter.notifyDataSetChanged()
                    }


                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })


    val clientCelebrity = AsyncHttpClient()
    clientCelebrity.get(CELEB_URL, object : JsonHttpResponseHandler() {
        override fun onFailure(
            statusCode: Int,
            headers: Headers?,
            response: String?,
            throwable: Throwable?
        ) {
            Log.e(TAG, "Failed to fetch articles: $statusCode")
        }

        override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
            Log.i(TAG, "Successfully fetched articles: $json")
            try {
                // TODO: Create the parsedJSON
                val parsedJson = createJson().decodeFromString(
                    SearchNewResultsCelebrity.serializer(),
                    json.jsonObject.toString()
                )


                // TODO: Do something with the returned json (contains article information)
                parsedJson.results?.let { list ->
                    celebrities.addAll(list)
                }

                // TODO: Save the articles and reload the screen
                parsedJson.results?.let { list ->
                    celebrities.addAll(list)

                    // Reload the screen
                    celebrityAdapter.notifyDataSetChanged()
                }


            } catch (e: JSONException) {
                Log.e(TAG, "Exception: $e")
            }
        }

    })

}
}