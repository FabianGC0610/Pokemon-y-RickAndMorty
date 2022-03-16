package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models.rickandmortymodels

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)
