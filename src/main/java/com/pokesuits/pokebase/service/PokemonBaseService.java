package com.pokesuits.pokebase.service;

import org.springframework.stereotype.Service;

import com.pokesuits.pokebase.entity.PokemonBaseEntity;
import com.pokesuits.pokebase.repository.PokemonBaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokemonBaseService {
	private final PokemonBaseRepository baseRepository;
	
	public PokemonBaseEntity getById(Integer idPokemon) {
		return baseRepository.findById(idPokemon).orElseThrow(null);
	}
}
