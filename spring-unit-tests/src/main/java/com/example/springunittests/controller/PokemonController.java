package com.example.springunittests.controller;

import com.example.springunittests.dto.PokemonDto;
import com.example.springunittests.service.impl.PokemonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class PokemonController {

    private final PokemonServiceImpl pokemonService;

    public PokemonController(PokemonServiceImpl pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("pokemon")
    public ResponseEntity<List<PokemonDto>> getAllPokemon() {
        return ResponseEntity.ok(pokemonService.getAllPokemon());

    }


    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> findPokemonById(@PathVariable int id) {
        return ResponseEntity.ok(pokemonService.getPokemonById(id));

    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }


    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemonById(@RequestBody PokemonDto pokemonDto, @PathVariable("id") int pokemonId) {
        PokemonDto response = pokemonService.updatePokemon(pokemonDto, pokemonId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemonById(@PathVariable("id") int pokemonId) {
        pokemonService.deletePokemonId(pokemonId);
        return new ResponseEntity<>("Pokemon delete", HttpStatus.OK);
    }
}
