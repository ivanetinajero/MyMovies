package net.itinajero.moviesapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.itinajero.moviesapp.databinding.ViewMovieItemBinding
import net.itinajero.moviesapp.model.Movie

/**
 * Es el componente dentro del adapter que va contener la vista
 */
class ViewHolder(private val binding : ViewMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie : Movie){
        binding.title.text = movie.title
        // https://github.com/bumptech/glide
        Glide.with(binding.root.context)
            .load("https://image.tmdb.org/t/p/w185/${movie.poster_path}")
            .into(binding.cover);
    }

}