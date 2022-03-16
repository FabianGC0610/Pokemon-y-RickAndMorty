package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.ui.pokemon

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.practicakodemiamvvmpokemonrickandmorty.data.models.Pokemon
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.databinding.FragmentPokemonBinding
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models.ErrorResponse

class PokemonFragment : Fragment() {

    var binding: FragmentPokemonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val viewmodel: PokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(PokemonViewModel::class.java)

        binding = FragmentPokemonBinding.inflate(inflater, container, false)

        observers()
        viewmodel.getPokemons()

        return binding!!.root
    }

    private fun observers() {
        viewmodel.pokemones.observe(viewLifecycleOwner,::mostrarPokemones)
        viewmodel.error.observe(viewLifecycleOwner,::errores)
        viewmodel.cargando.observe(viewLifecycleOwner,::cargando)
    }

    private fun cargando(b: Boolean) {

    }

    private fun errores(errorResponse: ErrorResponse) {
        Toast.makeText(context,errorResponse.message,Toast.LENGTH_SHORT).show()
    }

    private fun mostrarPokemones(pokemones: ArrayList<Pokemon>) {
        pokemones.forEach {
            Log.d("Resultado","El pokemon es ${it.name}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}