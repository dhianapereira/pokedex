package com.example.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.data.Endpoints
import com.example.pokedex.data.PokemonRepository
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.domain.Pokemon

class MainActivity : ComponentActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Thread {
            val response = PokemonRepository.getAll()
            val pokemons: List<Pokemon> = response?.let {
                it.results.mapNotNull { result ->
                    val number = result.url.replace(
                        Endpoints.BASE_URL + Endpoints.POKEMON,
                        ""
                    ).replace("/", "").toInt()
                    val pokemonApiResponse = PokemonRepository.getByNumber(number)
                    pokemonApiResponse?.let { pokemon ->
                        Pokemon(
                            pokemon.id,
                            pokemon.name,
                            pokemon.types.map { pokemonTypeSlot ->
                                pokemonTypeSlot.type
                            }
                        )
                    }
                }
            } ?: emptyList()

            val layoutManager = LinearLayoutManager(this)
            binding.pokemonRecycleView.post {
                binding.pokemonRecycleView.layoutManager = layoutManager
                binding.pokemonRecycleView.adapter = PokemonAdapter(pokemons)
            }
        }.start()
    }
}
