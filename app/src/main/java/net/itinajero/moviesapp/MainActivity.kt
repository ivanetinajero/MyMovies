package net.itinajero.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.itinajero.moviesapp.databinding.ActivityMainBinding
import net.itinajero.moviesapp.model.Movie
import net.itinajero.moviesapp.service.MovieDbClient
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    /**
     * Método ejecutado cuando se crea el Activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Acceso a componentes de la vista con View Binding
         */
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // El adapter es para indicar como se van a pintar las vistas
        val moviesAdapter = MoviesAdapter(
            emptyList()
            ,
            object: MovieClickedListener{
                override fun onMovieClicked(movie: Movie) {
                    Toast.makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT).show()
                }
            }

        )

        binding.recycler.adapter = moviesAdapter

        // Actualizacion de la vista con Corrutinas

        lifecycleScope.launch {

            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDbClient.service.listPopularMovies(apiKey)

            Log.d("resultado", "Peliculas encontradas: ${popularMovies.results.size}")
            moviesAdapter.movies = popularMovies.results
            moviesAdapter.notifyDataSetChanged()

        }

        //Nos salimos del hilo principal para no interrumpir la interaccion con el usuario al momento de estar ejecutando el REST API.
        /*
        thread {
            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDbClient.service.listPopularMovies(apiKey)
            val body = popularMovies.execute().body()
             //Despues de que termina la peticion al REST API, nos regresamos al hilo principal.
             //En terminos sencillos lo que este dentro del bloque runOnUiThread se ejecutara dentro del hilo principal.
            runOnUiThread{
                if (body != null) {
                    //Log.d("resultado", "Peliculas encontradas: ${body.results.size}")
                    moviesAdapter.movies = body.results
                    moviesAdapter.notifyDataSetChanged()
                }
            }

        }
        */

        /**
         * Nos salimos del hilo principal para no interrumpir la interaccion con el usuario al momento de estar ejecutando
         * el REST API. Con esta solucion tenemos el problema que no podremos pintar nada en la interfaz del usuario desde otro
         * hilo que no sea el hilo principal.
         */
        /*
        thread{
           val apiKey = getString(R.string.api_key)
           val popularMovies = MovieDbClient.service.listPopularMovies(apiKey)
           val body = popularMovies.execute().body()
           if (body != null) {
               Log.d("resultado", "Peliculas encontradas: ${body.results.size}")
           }
        }
        */

        // Forma tradicional de acceder a componentes de la vista
        /*
        setContentView(R.layout.activity_main)
        val message = findViewById<TextView>(R.id.message)
        message.text = "Hello Android"
        */

        Log.d("Principal", "OnCreate" )

    }

    /**
     * Método que se ejecuta cuando el Activity se destruye.
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Principal", "onDestroy" )
    }

    /**
     * Método que se ejecuta cuando el Activity pasa a primer plano
     */
    override fun onStart() {
        super.onStart()
        Log.d("Principal", "onStart" )
    }

    /**
     * Método que se ejecuta cuando el Activity pasa a segundo plano
     */
    override fun onStop() {
        super.onStop()
        Log.d("Principal", "onStop" )
    }

    /**
     * Método que se ejecuta cuando el Activity continua su ejecucion despues de algo que la estaba bloqueando.
     * Por ejemplo cuando llega una notificacion o aparece un dialogo
     */
    override fun onResume() {
        super.onResume()
        Log.d("Principal", "onResume" )
    }

    /**
     * Método que se ejecuta cuando hay algo por encima del Activity y la esta bloqueando.
     * Por ejemplo cuando llega una notificacion o aparece un dialogo
     */
    override fun onPause() {
        super.onPause()
        Log.d("Principal", "onPause" )
    }
}