package com.example.newmoviessample.models

import androidx.recyclerview.widget.DiffUtil

data class ServiceResponse(val page: Long?, var type: String,val results: List<Result>?)

data class Result(val id: Long?, val poster_path: String?, val title: String?)

data class GlobalObject(
    val nowPlaying: MoviesEntity,
    val upComing: MoviesEntity,
    val popular: MoviesEntity,
    val topRated: MoviesEntity,
)

data class MoviesEntity(
    var type: String? = "",
    var nowResults: List<Result>? = emptyList(),
    var upComingResults: List<Result>? = emptyList(),
    var popularResults: List<Result>? = emptyList(),
    var topRatedResults: List<Result>? = emptyList()
)

val diffutils = object : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}