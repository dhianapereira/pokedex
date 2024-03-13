package com.example.pokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.entities.Pokemon
import com.example.pokedex.domain.use_cases.GetAllPokemonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonListViewModel(private val getAllPokemons: GetAllPokemonsUseCase) : ViewModel() {
    private val uiState: MutableLiveData<PokemonListUiState> by lazy {
        MutableLiveData<PokemonListUiState>().also { liveData ->
            viewModelScope.launch {
                val pokemons = withContext(Dispatchers.IO) {
                    getAllPokemons.call()
                }
                liveData.value = PokemonListUiState(list = pokemons)
            }
        }
    }

    fun state(): LiveData<PokemonListUiState> = uiState

    data class PokemonListUiState(val list: List<Pokemon>)

    @Suppress("UNCHECKED_CAST")
    class Factory(private val getAllPokemons: GetAllPokemonsUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PokemonListViewModel(getAllPokemons) as T
        }
    }
}