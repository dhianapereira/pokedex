package com.example.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.domain.use_cases.GetAllPokemonsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)

        scope.launch {
            val pokemons = withContext(Dispatchers.IO) {
                GetAllPokemonsUseCase().call()
            }

            binding.pokemonRecycleView.layoutManager = layoutManager
            binding.pokemonRecycleView.adapter = PokemonAdapter(pokemons)
        }
    }
}
