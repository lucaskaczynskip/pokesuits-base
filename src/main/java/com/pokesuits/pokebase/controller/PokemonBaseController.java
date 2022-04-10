package com.pokesuits.pokebase.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokesuits.pokebase.dto.PokemonBaseDTO;
import com.pokesuits.pokebase.service.PokemonBaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pokemonbase")
@RequiredArgsConstructor
public class PokemonBaseController {
	private final PokemonBaseService baseService;
	
	@GetMapping("/todos-pokemonbase")
	public List<PokemonBaseDTO> todosPokemonbase(){
		return baseService.todosPokemonbase();
	}

	@GetMapping("/quantidade-pokemons-por-tipo")
	public String quantidadeDePokemonsPorTipo() {
		return baseService.quantidadePokemonsPorTipo();
	}
}
