package net.itinajero.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import net.itinajero.moviesapp.databinding.ActivityDetailBinding
import net.itinajero.moviesapp.model.Movie

class DetailActivity : AppCompatActivity() {

    // Similar a atributos estaticos de una clase
    companion object{
        const val EXTRA_TITLE = "DetailActivity:title"
        const val EXTRA_MOVIE = "DetailActivity:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperamos datos del intent
        /*
        val title = intent.getStringExtra(EXTRA_TITLE)
        binding.title.text = title
        */

        val movie  = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (movie != null) {
            // Indicamos el titulo asociado a esta Activity
            title = movie.title
            // Renderizamos la imagen
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w780/${movie.backdrop_path}")
                .into(binding.backdrop)
            binding.summary.text = movie.overview
            bindDetailInfo(binding.detailInfo, movie)
        }

        // This call requires API level 33
        // val movie = intent.getParcelableExtra(EXTRA_MOVIE, Movie::class.java)
    }

    private fun bindDetailInfo(detailInfo: TextView, movie: Movie) {
        // Mostramos parte de los detalles en texto plano. En este caso el idioma
        //detailInfo.text = "Original language: ${movie.original_language}";

        // Mostramos los detalles en texto con formato
        detailInfo.text = buildSpannedString {

            bold { append("Idioma Original: ") }
            appendLine(movie.original_language)

            bold { append("Titulo Original: ") }
            appendLine(movie.original_title)

            bold { append("Fecha lanzamiento: ") }
            appendLine(movie.release_date)

            bold { append("Popularidad: ") }
            appendLine(movie.popularity.toString())

            bold { append("Votos promedio: ") }
            appendLine(movie.vote_average.toString())
        }
    }
}