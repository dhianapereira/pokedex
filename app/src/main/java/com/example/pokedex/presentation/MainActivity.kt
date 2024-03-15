package com.example.pokedex.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.domain.entities.Pokemon
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

        viewModel.state().observe(this) { state ->
            when (state) {
                is PokemonListViewModel.PokemonListUiState.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                    binding.pokemonRecycleView.visibility = View.GONE
                }

                is PokemonListViewModel.PokemonListUiState.Success -> {
                    setRecycleView(state.list)
                }
            }
        }
    }

    private fun setRecycleView(list: List<Pokemon>) {
        binding.loading.visibility = View.GONE
        binding.pokemonRecycleView.visibility = View.VISIBLE
        val layoutManager = LinearLayoutManager(this)
        binding.pokemonRecycleView.layoutManager = layoutManager
        binding.pokemonRecycleView.adapter = PokemonAdapter(list)
    }
}
