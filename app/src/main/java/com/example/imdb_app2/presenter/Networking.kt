package com.example.imdb_app2.presenter

import com.example.imdb_app2.model.Movies_data
import com.example.imdb_app2.model.api_urls
import com.example.imdb_app2.model.stored_data
import com.example.imdb_app2.model.urls_type
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException


class Api_processing {

    private val client = OkHttpClient()
    var gson = Gson()

    fun run(url_type: urls_type) {

        var url: String = when (url_type) {
            urls_type.TOP -> api_urls.top_movies
            urls_type.LATEST -> api_urls.latest_movies
            urls_type.POPULAR -> api_urls.popular_movies
            else -> api_urls.popular_movies
        }
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                val resString = response.body()?.string()
                val returnedObj = gson?.fromJson(
                    resString,
                    Movies_data.json_movies::class.java
                )
                if (url_type == urls_type.TOP)
                    stored_data.set_top_movie_list(returnedObj)
                if (url_type == urls_type.LATEST)
                    stored_data.set_upcoming_movies(returnedObj)
                if (url_type == urls_type.POPULAR)
                    stored_data.set_popular_movies(returnedObj)

                println(returnedObj.results[2].original_title)
            }
        })
    }

    fun get_name_list(mov_type: urls_type): List<String> {
        return stored_data.get_array_movies_name(mov_type)
    }

    interface view {
        fun get_list(url_type: urls_type): Array<String>
    }
}

