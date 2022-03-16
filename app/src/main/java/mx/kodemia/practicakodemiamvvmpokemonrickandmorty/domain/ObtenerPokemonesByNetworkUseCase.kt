package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.domain

import com.example.practicakodemiamvvmpokemonrickandmorty.data.models.Pokemon
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.repository.RepositorioPokemonNetwork
import retrofit2.Response

class ObtenerPokemonesByNetworkUseCase {
    val service = RepositorioPokemonNetwork()

    suspend fun obtenerPokemones(): Response<ArrayList<Pokemon>> {
        val respuesta = service.obtenerPokemones().body()?.results
        return Response.success(respuesta)
    }
}