package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.ui.rickandmorty

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.card.MaterialCardView
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.R
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.databinding.ItemCardviewRickandmortyBinding
import mx.kodemia.practicakodemiamvvmpokemonrickandmorty.models.rickandmortymodels.Results

class RickAndMortyAdapter(val activity: Activity, val personajes: ArrayList<Results>): RecyclerView.Adapter<RickAndMortyAdapter.RickAndMortyHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyAdapter.RickAndMortyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_rickandmorty,parent,false)
        return RickAndMortyHolder(view)
    }

    override fun onBindViewHolder(holder: RickAndMortyAdapter.RickAndMortyHolder, position: Int) {
        val personaje = personajes.get(position)
        with(holder) {
            binding.apply {
                textViewNombre.text = personaje.name
                imageViewFoto.load(personaje.image)
            }
        }
    }

    override fun getItemCount(): Int = personajes.size

    class RickAndMortyHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemCardviewRickandmortyBinding.bind(view)
    }

}