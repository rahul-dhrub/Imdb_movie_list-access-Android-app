package com.example.imdb_app2.model
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
    fun set_popular_movies(list: Movies_data.json_movies) {
        popular_movies = list
    }

    fun set_top_movie_list(list: Movies_data.json_movies) {
        top_movies = list
    }

    fun set_upcoming_movies(list: Movies_data.json_movies) {
        upcoming_movies = list
    }

    fun append(arr: Array<Moviedetail>, element: Moviedetail): Array<Moviedetail> {
        val list: MutableList<Moviedetail> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

    fun get_array_movie_details(urlsType: urls_type): Array<Moviedetail> {
        val result: MutableList<Moviedetail> = ArrayList()
        try {
            when (urlsType) {
                urls_type.POPULAR -> {
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
                urls_type.TOP -> {
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
                urls_type.LATEST -> {
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

    fun get_array_movies_name(urlsType: urls_type): List<String> {
        try {
            var fetchObject: List<String> = when (urlsType) {
                urls_type.POPULAR -> popular_movies.results.map { it.original_title }
                urls_type.TOP -> top_movies.results.map { it.original_title }
                urls_type.LATEST -> upcoming_movies.results.map { it.original_title }
                else -> popular_movies
            } as List<String>
            return fetchObject
        } catch (e: NullPointerException) {
            println("Empty List" + e)
            return emptyList()
        }
    }

}


