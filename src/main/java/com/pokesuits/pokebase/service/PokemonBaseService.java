package com.pokesuits.pokebase.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pokesuits.pokebase.dto.PokemonBaseDTO;
import com.pokesuits.pokebase.dto.pokeAgregateDTO;
import com.pokesuits.pokebase.entity.PokemonBaseEntity;
import com.pokesuits.pokebase.exception.RegraDeNegocioException;
import com.pokesuits.pokebase.repository.PokemonBaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokemonBaseService {
	private final PokemonBaseRepository baseRepository;
	
	public PokemonBaseEntity getById(Integer idPokemon) throws RegraDeNegocioException {
		return baseRepository.findById(idPokemon).orElseThrow(()->new RegraDeNegocioException("não existe um pokemon base com o id passado"));
	}
	
	public List<PokemonBaseDTO> todosPokemonbase(){
		return baseRepository.findAll().stream()
				.map(p-> {
					PokemonBaseDTO baseDTO = new PokemonBaseDTO(p.getId(), p.getRacaPokemon(), p.getPesoMinimo(), 
							p.getPesoMaximo(), p.getPorcentagemMacho(), p.getLevelMinimo(), p.getDificuldade(), 
							p.getTipos().get(0), (p.getTipos().size()>1)?p.getTipos().get(1):null, p.getRaridade());
					return baseDTO;
				})
				.collect(Collectors.toList());
	}

	public List<PokemonBaseDTO> getByTipo(String tipo) {
		return baseRepository.findByTipos(tipo).stream()
				.map(p -> {
					PokemonBaseDTO baseDTO = new PokemonBaseDTO(p.getId(), p.getRacaPokemon(), p.getPesoMinimo(), 
							p.getPesoMaximo(), p.getPorcentagemMacho(), p.getLevelMinimo(), p.getDificuldade(), 
							p.getTipos().get(0), (p.getTipos().size()>1)?p.getTipos().get(1):null, p.getRaridade());
					return baseDTO;
				}).collect(Collectors.toList());
				
	}


	public List<pokeAgregateDTO> quantidadePokemonsPorTipo() {
		return this.baseRepository.groupByTipoAndCount().stream().map(p->{
			pokeAgregateDTO agregateDTO = new pokeAgregateDTO(p.getId(), p.getQuantidade());
			return agregateDTO;
		}).collect(Collectors.toList());
	}
}
