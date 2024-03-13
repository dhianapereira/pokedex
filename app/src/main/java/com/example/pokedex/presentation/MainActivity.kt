package com.example.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.domain.use_cases.GetAllPokemonsUseCase

class MainActivity : ComponentActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Thread {
            val pokemons = GetAllPokemonsUseCase().call()
            val layoutManager = LinearLayoutManager(this)
            binding.pokemonRecycleView.post {
                binding.pokemonRecycleView.layoutManager = layoutManager
                binding.pokemonRecycleView.adapter = PokemonAdapter(pokemons)
            }
        }.start()
    }
}
