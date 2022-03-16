package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.repository

import com.example.practicakodemiamvvmpokemonrickandmorty.core.RetrofitInstance
import com.example.practicakodemiamvvmpokemonrickandmorty.data.models.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.core.ApiPokemon
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.core.ApiRickAndMorty
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models.rickandmortymodels.RickAndMortyResponse
import retrofit2.Response

class RepositorioPersonajesNetwork {
    val retrofit = RetrofitInstance.getRetrofitRickAndMorty().create(ApiRickAndMorty::class.java)

    suspend fun obtenerPersonajes(page: Int): Response<RickAndMortyResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.obtenerPersonajes(page)
            response
        }
    }
}