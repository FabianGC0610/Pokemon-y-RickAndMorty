package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.ui.pokemon

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.practicakodemiamvvmpokemonrickandmorty.data.models.Pokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.domain.ObtenerPokemonesByNetworkUseCase
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models.ErrorResponse
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class PokemonViewModelTest{

    private lateinit var viewModel: PokemonViewModel

    @RelaxedMockK
    private lateinit var casoUso : ObtenerPokemonesByNetworkUseCase

    @get:Rule
    var schedulers : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModel = PokemonViewModel()
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel.useCase = casoUso
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun checarPokemonesCasodeUso() = runTest {
        val pokemones = ArrayList<Pokemon>()
        pokemones.add(Pokemon("Pokemon 1"))
        pokemones.add(Pokemon("Pokemon 2"))

        coEvery { casoUso.obtenerPokemones() } returns Response.success(pokemones)

        viewModel.getPokemons()

        assert(viewModel.pokemones.value == pokemones)
    }

    @Test
    fun checarError() = runTest{
        val string ="{" +
                "protocol:\"h2\",\n" +
                " code:400,\n" +
                " message:\"error\", \n" +
                "url:\"https://rickandmortyapi.com/api/character\"\n" +
                "}"
        val response = ResponseBody.create(MediaType.parse("text/*"),string)

        coEvery { casoUso.obtenerPokemones() } returns Response.error(404, response)

        viewModel.getPokemons()

        val errorViewModel = viewModel.error.value as ErrorResponse
        assert(errorViewModel.code == 404)
    }

}