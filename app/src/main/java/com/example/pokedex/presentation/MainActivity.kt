package com.example.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.domain.PokemonType

class MainActivity : ComponentActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pokemon = Pokemon("", 1, "Bulbasaur", listOf(PokemonType("")))
        val element = listOf<Pokemon>(
            pokemon, pokemon, pokemon,
            pokemon, pokemon, pokemon
        )

        val layoutManager = LinearLayoutManager(this)
        binding.pokemonRecycleView.layoutManager = layoutManager
        binding.pokemonRecycleView.adapter = PokemonAdapter(element)
    }
}