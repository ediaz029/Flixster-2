package com.codepath.articlesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val C_EXTRA = "CELEBRITY_EXTRA"
private const val TAG = "CelebrityAdapter"

class CelebrityAdapter(private val context: Context, private val celebrities: List<Celebrity>) :
    RecyclerView.Adapter<CelebrityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_celebrity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val celebrity = celebrities[position]
        holder.bind(celebrity)
    }

    override fun getItemCount() = celebrities.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val celebImageView = itemView.findViewById<ImageView>(R.id.celebMediaImage)
        private val nameTextView = itemView.findViewById<TextView>(R.id.celebName)


        init {
            itemView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(celebrity: Celebrity) {
            nameTextView.text = celebrity.name

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + celebrity.imageUrl)
                .into(celebImageView)
        }

        override fun onClick(v: View?) {
            // TODO: Get selected article
            val celebrity = celebrities[absoluteAdapterPosition]

            // TODO: Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivityCeleb::class.java)
            intent.putExtra(C_EXTRA, celebrity)
            context.startActivity(intent)
        }
    }
}