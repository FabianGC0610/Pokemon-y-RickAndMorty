package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models

data class ErrorResponse(
    val protocol: String?,
    val code: Int?,
    val message: String?,
    val url: String?
)
