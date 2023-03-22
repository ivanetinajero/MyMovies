package net.itinajero.moviesapp.model

import com.google.gson.annotations.SerializedName

data class MovieDbResult(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)