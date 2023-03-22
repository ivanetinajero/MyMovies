package net.itinajero.moviesapp

import net.itinajero.moviesapp.model.Movie

interface MovieClickedListener {
    fun onMovieClicked(movie: Movie)
}