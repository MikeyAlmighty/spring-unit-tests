package com.example.springunittests.service;

import com.example.springunittests.dto.PokemonDto;
import com.example.springunittests.model.Pokemon;
import com.example.springunittests.repository.PokemonRepository;
import com.example.springunittests.service.impl.PokemonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    public void PokemonService_Create_Returns_PokemonDto() {
        // given
        Pokemon mockPokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        PokemonDto pokemonDto = PokemonDto.builder()
                .name("Pikachu")
                .type("Electric")
                .build();
        // when
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(mockPokemon);
        PokemonDto savedPokemon = pokemonService.createPokemon(pokemonDto);

        // then
        Assertions.assertThat(savedPokemon).isNotNull();
        Assertions.assertThat(savedPokemon.getName()).isEqualTo("Pikachu");
    }

    @Test
    public void PokemonService_FindById_Returns_PokemonDto() {
        // given
        Pokemon mockPokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        PokemonDto pokemonDto = PokemonDto.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        // when
        when(pokemonRepository.findById(1)).thenReturn(Optional.of(mockPokemon));
        PokemonDto savedPokemon = pokemonService.getPokemonById(1);

        // then
        Assertions.assertThat(savedPokemon).isNotNull();
    }

    @Test
    public void PokemonService_Update_Returns_PokemonDto() {
        // given
        Pokemon mockPokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        PokemonDto pokemonDto = PokemonDto.builder()
                .name("Pikachu")
                .type("Fighting")
                .build();

        // when
        when(pokemonRepository.findById(1)).thenReturn(Optional.of(mockPokemon));
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(mockPokemon);

        PokemonDto updatedPokemon = pokemonService.updatePokemon(pokemonDto, 1);

        // then
        Assertions.assertThat(updatedPokemon).isNotNull();
        Assertions.assertThat(updatedPokemon.getType()).isEqualTo("Fighting");
    }


    @Test
    public void PokemonService_DeleteById() {
        // given
        Pokemon mockPokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        // when
        when(pokemonRepository.findById(1)).thenReturn(Optional.of(mockPokemon));

        // then
        assertAll(() -> pokemonService.deletePokemonId(1));
    }
}
