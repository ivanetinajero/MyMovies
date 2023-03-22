package net.itinajero.moviesapp.service

import net.itinajero.moviesapp.model.MovieDbResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IMoviesService {

    @GET("movie/popular")
    //fun listPopularMovies(@Query("api_key") apiKey: String): Call<MovieDbResult>
    // PAra usar Corrutinas
    suspend fun listPopularMovies(@Query("api_key") apiKey: String): MovieDbResult
}