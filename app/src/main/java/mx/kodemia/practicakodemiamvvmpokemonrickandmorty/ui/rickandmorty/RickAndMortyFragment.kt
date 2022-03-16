package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.ui.rickandmorty

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.databinding.FragmentGalleryBinding
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models.ErrorResponse
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models.rickandmortymodels.Results
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.pages.pageVerificacation
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.ui.pokemon.PokemonViewModel

class RickAndMortyFragment : Fragment() {

    var binding: FragmentGalleryBinding? = null

    var pagina = 1

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val viewmodel: RickAndMortyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(RickAndMortyViewModel::class.java)

        binding = FragmentGalleryBinding.inflate(inflater, container, false)

        observers()
        viewmodel.getPersonajes(1)

        binding?.apply {

            pageVerificacation(pagina,buttonNext,buttonReturn)

            buttonNext.setOnClickListener {
                pagina++
                pageVerificacation(pagina,buttonNext,buttonReturn)
                viewmodel.getPersonajes(pagina)
            }

            buttonReturn.setOnClickListener {
                pagina--
                pageVerificacation(pagina,buttonNext,buttonReturn)
                viewmodel.getPersonajes(pagina)
            }
        }


        return binding!!.root
    }

    private fun observers() {
        viewmodel.personajes.observe(viewLifecycleOwner,::mostrarPersonajes)
        viewmodel.error.observe(viewLifecycleOwner,::errores)
        viewmodel.cargando.observe(viewLifecycleOwner,::cargando)
    }

    private fun cargando(b: Boolean) {

    }

    private fun errores(errorResponse: ErrorResponse) {
        Toast.makeText(context,errorResponse.message, Toast.LENGTH_SHORT).show()
    }

    private fun mostrarPersonajes(personajes: ArrayList<Results>) {
        personajes.forEach {
            Log.d("Resultado Rick And Morty","El personaje es ${it.name}")
            initRecycler(personajes,binding?.recyclerViewRickandmorty)
        }
    }

    private fun initRecycler(lista: ArrayList<Results>, recyclerView: RecyclerView?){
        val adaptador = RickAndMortyAdapter(requireActivity(),lista)
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adaptador
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}