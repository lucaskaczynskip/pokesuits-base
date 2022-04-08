package com.pokesuits.pokebase.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pokesuits.pokebase.entity.PokemonBaseEntity;

@Repository
public interface PokemonBaseRepository extends MongoRepository<PokemonBaseEntity, Integer>  {
    
}
