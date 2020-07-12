package com.example.imdb_app2.model

object api_urls
{
    val popular_movies : String =    "https://api.themoviedb.org/3/movie/popular?api_key=0de9947f74b0a13182547181e49a324b&language=en-US&page=1"
    val top_movies : String =    "https://api.themoviedb.org/3/movie/top_rated?api_key=0de9947f74b0a13182547181e49a324b&language=en-US&page=1"
    val latest_movies : String =    "https://api.themoviedb.org/3/movie/upcoming?api_key=0de9947f74b0a13182547181e49a324b&language=en-US&page=1"
}

enum class urls_type (val type: String){POPULAR ("Popular"), TOP  ("Top"), LATEST  ("Latest")}