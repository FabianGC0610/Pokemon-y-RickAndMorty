package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.ui.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicakodemiamvvmpokemonrickandmorty.data.models.Pokemon
import com.google.gson.Gson
import kotlinx.coroutines.launch
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.domain.ObtenerPokemonesByNetworkUseCase
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models.ErrorResponse
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.repository.RepositorioPokemonNetwork
import java.io.IOException

class PokemonViewModel : ViewModel() {

    //LiveDatas
    val pokemones = MutableLiveData<ArrayList<Pokemon>>()
    val error = MutableLiveData<ErrorResponse>()
    val cargando = MutableLiveData<Boolean>()

    //Casos de uso
    var useCase = ObtenerPokemonesByNetworkUseCase()

    fun getPokemons(){
        viewModelScope.launch {
            cargando.postValue(true)
            val respuesta = useCase.obtenerPokemones()
            try {
                if(respuesta.isSuccessful){
                    pokemones.postValue(respuesta.body())
                }else if(respuesta.code() == 404) {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(respuesta.errorBody().toString(),ErrorResponse::class.java)
                    error.postValue(errorResponse)
                }else{
                    //error.postValue(respuesta.errorBody().toString())
                }
                cargando.postValue(false)
            }catch (io: IOException){
                error.postValue(io.message?.let { ErrorResponse(null, 404,null,null) })
                //error.postValue(io.message?.let { ErrorResponse(io.hashCode(), it) })
            cargando.postValue(false)
            //error.postValue(io.message)
            }
        }
    }
}