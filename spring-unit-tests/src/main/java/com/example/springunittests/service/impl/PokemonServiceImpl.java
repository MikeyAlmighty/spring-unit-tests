package com.example.springunittests.service.impl;

import com.example.springunittests.dto.PokemonDto;
import com.example.springunittests.exception.PokemonNotFoundException;
import com.example.springunittests.model.Pokemon;
import com.example.springunittests.repository.PokemonRepository;
import com.example.springunittests.service.PokemonService;
import com.example.springunittests.util.PokemonUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.springunittests.util.PokemonUtils.mapToDto;

@Service
public class PokemonServiceImpl implements PokemonService {
    private final PokemonRepository pokemonRepository;

    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);
        return mapToDto(newPokemon);
    }

    @Override
    public List<PokemonDto> getAllPokemon() {
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        return pokemonList.stream().map(PokemonUtils::mapToDto).toList();
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));
        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be updated"));

        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon updatedPokemon = pokemonRepository.save(pokemon);
        return mapToDto(updatedPokemon);
    }

    @Override
    public void deletePokemonId(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be deleted"));
        pokemonRepository.delete(pokemon);
    }
}