package net.itinajero.moviesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView
import net.itinajero.moviesapp.databinding.ViewMovieItemBinding
import net.itinajero.moviesapp.model.Movie

class MoviesAdapter(var movies: List<Movie>, private val movieClickedListener: MovieClickedListener) :RecyclerView.Adapter<ViewHolder>() {

    /**
     * Metodo encargado de crear una nueva vista cuando el RecyclerView se lo pida
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    /**
     * Encargado de actualizar la vista uando
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener{movieClickedListener.onMovieClicked(movie)}
    }

    /**
     * Regresamos en numero de elementos que tiene el adapter
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * En base a la posicion del elemento, especificamos el tipo de vista
     */
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

}