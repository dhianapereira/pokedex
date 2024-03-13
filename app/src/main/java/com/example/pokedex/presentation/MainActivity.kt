package com.example.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.domain.use_cases.GetAllPokemonsUseCase

class MainActivity : ComponentActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: PokemonListViewModel by viewModels {
        PokemonListViewModel.Factory(GetAllPokemonsUseCase())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.pokemonRecycleView.layoutManager = layoutManager
        binding.pokemonRecycleView.adapter = PokemonAdapter(viewModel)
    }
}
