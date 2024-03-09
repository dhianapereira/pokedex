package com.example.pokedex.domain

data class Pokemon(
    val imageUrl: String,
    val number: Number,
    val name: String,
    val types: List<PokemonType>
) {
    fun formattedNumber(): String {
        return "Nº ${number.toString().padStart(3, '0')}"
    }
}