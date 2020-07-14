package com.example.imdb_app2.model

import android.util.Log

//import kotlinx.serialization.*
//import kotlinx.serialization.json.JSON

class Movies_data {

    data class json_movies(
        val page: Int,
        val total_results: Int,
        val total_pages: Int,
        val results: List<result_data>
    )

    data class result_data(
        val popularity: Double,
        val vote_count: Int,
        val video: Boolean,
        val poster_path: String,
        val id: Int,
        val adult: Boolean,
        val backdrop_path: String,
        val original_language: String,
        val original_title: String,
        val genre_ids: List<Int>,
        val title: String,
        val vote_average: Double,
        val overview: String,
        val release_date: String
    )
}

data class Moviedetail(val Image_Url: String, val Name: String, val ReleaseDate: String)

object stored_data {
    lateinit var popular_movies: Movies_data.json_movies
    lateinit var top_movies: Movies_data.json_movies
    lateinit var upcoming_movies: Movies_data.json_movies
    fun setPopularMovies(list: Movies_data.json_movies) {
        popular_movies = list
    }

    fun setTopMovieList(list: Movies_data.json_movies) {
        top_movies = list
    }

    fun setUpcomingMovies(list: Movies_data.json_movies) {
        upcoming_movies = list
    }

    fun getArrayMovieDetails(urlsType: UrlsType): Array<Moviedetail> {
        val result: MutableList<Moviedetail> = ArrayList()
        try {
            when (urlsType) {
                UrlsType.POPULAR -> {
                    for (elm in popular_movies.results) {
                        result.add(
                            Moviedetail(
                                Image_Url = elm.poster_path,
                                Name = elm.title,
                                ReleaseDate = elm.release_date
                            )
                        )
                    }
                }
                UrlsType.TOP -> {
                    for (elm in top_movies.results) {
                        result.add(
                            Moviedetail(
                                Image_Url = elm.poster_path,
                                Name = elm.title,
                                ReleaseDate = elm.release_date
                            )
                        )
                    }
                }
                UrlsType.LATEST -> {
                    for (elm in upcoming_movies.results) {
                        result.add(
                            Moviedetail(
                                Image_Url = elm.poster_path,
                                Name = elm.title,
                                ReleaseDate = elm.release_date
                            )
                        )
                    }
                }
                else -> TODO()
            }
        } catch (e: NullPointerException) {
        }
        return result.toTypedArray()
    }

    fun getArrayMoviesName(urlsType: UrlsType): List<String> {
        return try {
            val fetchObject: List<String> = when (urlsType) {
                UrlsType.POPULAR -> popular_movies.results.map { it.original_title }
                UrlsType.TOP -> top_movies.results.map { it.original_title }
                UrlsType.LATEST -> upcoming_movies.results.map { it.original_title }
                else -> popular_movies
            } as List<String>
            fetchObject
        } catch (e: NullPointerException) {
            Log.i("CustomErrorMessage", "Empty List$e")
            emptyList()
        }
    }

}


