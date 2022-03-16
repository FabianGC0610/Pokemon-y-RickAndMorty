package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.core

import com.example.practicakodemiamvvmpokemonrickandmorty.data.models.PokemonResponse
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models.rickandmortymodels.RickAndMortyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRickAndMorty {
    @GET("api/character")
    suspend fun obtenerPersonajes (@Query("page") page: Int): Response<RickAndMortyResponse>
}