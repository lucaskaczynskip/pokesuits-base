package com.pokesuits.pokebase.repository;

import com.pokesuits.pokebase.entity.PokemonPorTiposEntity;
import com.pokesuits.pokebase.enums.TipoPokemon;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pokesuits.pokebase.entity.PokemonBaseEntity;

import java.util.List;

@Repository
public interface PokemonBaseRepository extends MongoRepository<PokemonBaseEntity, Integer>  {
    @Aggregation(pipeline = {
            "{'$match':{ 'tipo': { '$in': ['BUG', 'DARK', 'DRAGON', 'ELECTRIC', 'FAIRY', 'FIGHTING', 'FIRE', 'FLYING', 'GHOST', 'GRASS', 'GROUND', 'ICE', 'NORMAL', 'POISON', 'PSYCHIC', 'ROCK', 'STEEL', 'WATER'] } }}",
            "{'$group':{ '_id': '$tipo', 'quantidade': {'$sum': 1} }}"
    })
    List<PokemonPorTiposEntity> groupByTipoAndCount();

//    @Aggregation(pipeline = {
//            "{'$match':{ 'tipo2': { '$in': ['BUG', 'DARK', 'DRAGON', 'ELECTRIC', 'FAIRY', 'FIGHTING', 'FIRE', 'FLYING', 'GHOST', 'GRASS', 'GROUND', 'ICE', 'NORMAL', 'POISON', 'PSYCHIC', 'ROCK', 'STEEL', 'WATER'] } }}",
//            "{'$group':{ '_id': '$tipo2', 'quantidade': {'$sum': 1} }}"
//    })
//    List<PokemonPorTiposEntity> groupByTipo2AndCount();

    List<PokemonBaseEntity> findByTipo1OrTipo2(String tipo);
}
