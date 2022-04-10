package com.pokesuits.pokebase.service;

import java.util.ArrayList;
import java.util.Collections;
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

	public String quantidadePokemonsPorTipo() {
		List<PokemonPorTiposEntity> tipo1 = this.baseRepository.groupByTipo1AndCount();
		List<PokemonPorTiposEntity> tipo2 = this.baseRepository.groupByTipo2AndCount();
		
		List<String> tiposListaNomes = new ArrayList<>();

		tipo1.stream().map(tipo -> tiposListaNomes.addAll(Collections.singleton(tipo.getId())));
		tipo2.stream().forEach(tipo -> {
			tiposListaNomes.forEach(nome -> {
				if (nome.equals(tipo.getId())) tipo2.remove(tipo);
			});
		});

		StringBuilder string = new StringBuilder();

		tipo1.forEach(tipo -> {
					string.append("O tipo " + tipo.getId() + " tem uma quantidade de " + tipo.getQuantidade() + " pokemons.\n");
				});
		tipo2.forEach(tipo -> {
			string.append("O tipo " + tipo.getId() + " tem uma quantidade de " + tipo.getQuantidade() + " pokemons.\n");
		});

		return string.toString();
	}
}
