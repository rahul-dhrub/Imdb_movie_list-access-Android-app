package com.example.imdb_app2.presenter

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.imdb_app2.model.*
import com.google.gson.Gson
import org.json.JSONException


class Api_processing {

    var gson = Gson()
    lateinit var mRequestQueue: RequestQueue
    fun run(con: Context) {
        mRequestQueue = Volley.newRequestQueue(con)
        parseJSON(ApiUrls.latest_movies, UrlsType.LATEST)
        parseJSON(ApiUrls.popular_movies, UrlsType.POPULAR)
        parseJSON(ApiUrls.top_movies, UrlsType.TOP)
    }

    fun get_name_list(mov_type: UrlsType): List<String> {
        return stored_data.getArrayMoviesName(mov_type)
    }

    fun get_detail_list(mov_type: UrlsType): Array<Moviedetail> {
        return stored_data.getArrayMovieDetails(mov_type)
    }

    interface view {
        fun getData()
    }

    private fun parseJSON(url: String, url_type: UrlsType) {
        val url = url
        val request =
            JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->
                    try {
//                        Log.i("Response received", response.toString())
                        val returnedObj = gson.fromJson(
                            response.toString(),
                            Movies_data.json_movies::class.java
                        )
                        if (url_type == UrlsType.TOP)
                            stored_data.setTopMovieList(returnedObj)
                        if (url_type == UrlsType.LATEST)
                            stored_data.setUpcomingMovies(returnedObj)
                        if (url_type == UrlsType.POPULAR)
                            stored_data.setPopularMovies(returnedObj)
                        println(returnedObj.results[2].original_title)

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener { error -> error.printStackTrace() })
        mRequestQueue.add(request)
    }
}



