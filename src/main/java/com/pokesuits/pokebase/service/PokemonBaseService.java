package com.pokesuits.pokebase.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokesuits.pokebase.dto.PokemonBaseDTO;
import com.pokesuits.pokebase.entity.PokemonBaseEntity;
import com.pokesuits.pokebase.entity.PokemonPorTiposEntity;
import com.pokesuits.pokebase.exception.RegraDeNegocioException;
import com.pokesuits.pokebase.repository.PokemonBaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokemonBaseService {
	private final PokemonBaseRepository baseRepository;
	private final ObjectMapper objectMapper;
	
	public PokemonBaseEntity getById(Integer idPokemon) throws RegraDeNegocioException {
		return baseRepository.findById(idPokemon).orElseThrow(()->new RegraDeNegocioException("não existe um pokemon base com o id passado"));
	}
	
	public List<PokemonBaseDTO> todosPokemonbase(){
		return baseRepository.findAll().stream()
				.map(p-> objectMapper.convertValue(p, PokemonBaseDTO.class))
				.collect(Collectors.toList());
	}

	public List<PokemonBaseDTO> getByTipo(String tipo) {
		return baseRepository.findByTipo1OrTipo2(tipo).stream()
				.map(pokemon -> {
					PokemonBaseDTO pokemonBaseDTO = objectMapper.convertValue(pokemon, PokemonBaseDTO.class);
					pokemonBaseDTO.setId(pokemon.getId());
					return pokemonBaseDTO;
				})
				.collect(Collectors.toList());
	}


	public String quantidadePokemonsPorTipo() {
		List<PokemonPorTiposEntity> tipos = this.baseRepository.groupByTipoAndCount();

		StringBuilder string = new StringBuilder();
		tipos.forEach(tipo -> string.append("O tipo " + tipo.getId() + " tem uma quantidade de " + tipo.getQuantidade() + " pokemons.\n"));

		return string.toString();
	}
}
