package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.repository

import com.example.practicakodemiamvvmpokemonrickandmorty.core.RetrofitInstance
import com.example.practicakodemiamvvmpokemonrickandmorty.data.models.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.core.ApiPokemon
import retrofit2.Response

class RepositorioPokemonNetwork {

    val retrofit = RetrofitInstance.getRetrofit().create(ApiPokemon::class.java)

    suspend fun obtenerPokemones(): Response<PokemonResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.obtenerPokemones(200)
            response
        }
    }
}