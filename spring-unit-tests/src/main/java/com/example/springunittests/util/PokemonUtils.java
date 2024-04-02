package com.example.springunittests.util;

import com.example.springunittests.dto.PokemonDto;
import com.example.springunittests.model.Pokemon;

public class PokemonUtils {
    public static PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }
}
