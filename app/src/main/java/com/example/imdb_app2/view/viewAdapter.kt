package com.example.senthil.kotlin_recyclerview.Adapter

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb_app2.R
import com.example.imdb_app2.model.Moviedetail
import com.squareup.picasso.Picasso
import kotlin.math.log

class CustomRecyclerAdapter(val context: Context, private val MovieDetailList: Array<Moviedetail>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0?.Name?.text = MovieDetailList[p1].Name
        p0?.Year?.text = "Release Year : ${MovieDetailList[p1].ReleaseDate}"
//        Log.i("url","https://image.tmdb.org/t/p/w185"+MovieDetailList[p1].Image_Url)
        Picasso.with(context)
            .load("https://image.tmdb.org/t/p/original"+MovieDetailList[p1].Image_Url).into(p0.image)
//        p0?.image.setImageResource(   )
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v =
            LayoutInflater.from(p0?.context).inflate(R.layout.recycler_adapter_content, p0, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return MovieDetailList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Name = itemView.findViewById<TextView>(R.id.MovieTitle)
        val Year = itemView.findViewById<TextView>(R.id.ReleaseYear)
        val image = itemView.findViewById<ImageView>(R.id.Poster)
    }
}