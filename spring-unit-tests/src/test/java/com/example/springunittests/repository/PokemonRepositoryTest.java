package com.example.springunittests.repository;

import com.example.springunittests.model.Pokemon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTest {
    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    public void PokemonRepository_SaveAll_ReturnSavedPokemon(){
        // given
        Pokemon pokemon = Pokemon.builder()
                .name("Charmander")
                .type("Fire")
                .build();

        // when
        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        // then
        Assertions.assertThat(savedPokemon).isNotNull();
        Assertions.assertThat(savedPokemon.getId()).isGreaterThan(0);
    }

    @Test
    public void PokemonRepository_GetAll_Return_More_Than_One_Pokemon(){
        // given
        Pokemon pokemonOne = Pokemon.builder()
                .name("Charmander")
                .type("Fire")
                .build();
        Pokemon pokemonTwo = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();
        // when
        pokemonRepository.save(pokemonOne);
        pokemonRepository.save(pokemonTwo);
        List<Pokemon> pokemonList = pokemonRepository.findAll();

        // then
        Assertions.assertThat(pokemonList).isNotNull();
        Assertions.assertThat(pokemonList).hasSize(2);
    }

    @Test
    public void PokemonRepository_FindById_Should_Return_Pokemon(){
        // given
        Pokemon pokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        pokemonRepository.save(pokemon);
        // when
        Pokemon pokemonResponse = pokemonRepository.findById(pokemon.getId()).orElse(null);

        // then
        Assertions.assertThat(pokemonResponse).isNotNull();
        Assertions.assertThat(pokemonResponse.getName()).isEqualTo("Pikachu");
    }

    @Test
    public void PokemonRepository_FindByType_Should_Return_Pokemon(){
        // given
        Pokemon pokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        pokemonRepository.save(pokemon);

        // when
        Pokemon optionalPokemon = pokemonRepository.findByType(pokemon.getType()).get();

        // then
        Assertions.assertThat(optionalPokemon).isNotNull();
        Assertions.assertThat(optionalPokemon.getName()).isEqualTo("Pikachu");
    }

    @Test
    public void PokemonRepository_Update_Should_Return_Pokemon(){
        // given
        Pokemon pokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        pokemonRepository.save(pokemon);

        // when
        Pokemon pokemonSave = pokemonRepository.findByType(pokemon.getType()).get();
        pokemonSave.setName("Beedrill");
        pokemonSave.setType("Flying");

        Pokemon pokemonUpdated = pokemonRepository.save(pokemonSave);
        // then
        Assertions.assertThat(pokemonUpdated).isNotNull();
        Assertions.assertThat(pokemonUpdated.getName()).isEqualTo("Beedrill");
    }


    @Test
    public void PokemonRepository_Delete_Should_Remove_Pokemon(){
        // given
        Pokemon pokemonOne = Pokemon.builder()
                .name("Charmander")
                .type("Fire")
                .build();
        Pokemon pokemonTwo = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();
        pokemonRepository.save(pokemonOne);
        pokemonRepository.save(pokemonTwo);

        // when
        pokemonRepository.save(pokemonOne);
        pokemonRepository.save(pokemonTwo);

        pokemonRepository.deleteById(pokemonTwo.getId());

        List<Pokemon> pokemonList = pokemonRepository.findAll();

        // then
        Assertions.assertThat(pokemonList).isNotNull();
        Assertions.assertThat(pokemonList).hasSize(1);
    }
}
