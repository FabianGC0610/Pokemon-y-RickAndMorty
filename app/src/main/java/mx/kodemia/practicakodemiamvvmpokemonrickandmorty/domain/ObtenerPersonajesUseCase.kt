package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.domain

import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models.rickandmortymodels.Results
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.repository.RepositorioPersonajesNetwork
import retrofit2.Response

class ObtenerPersonajesUseCase {
    val service = RepositorioPersonajesNetwork()

    suspend fun obtenerPersonajes(page: Int): Response<ArrayList<Results>> {
        val respuesta = service.obtenerPersonajes(page).body()?.results
        return Response.success(respuesta)
    }
}