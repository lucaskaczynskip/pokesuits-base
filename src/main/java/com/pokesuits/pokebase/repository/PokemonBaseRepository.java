package com.pokesuits.pokebase.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pokesuits.pokebase.entity.PokemonBaseEntity;
import com.pokesuits.pokebase.entity.PokemonPorTiposEntity;

@Repository
public interface PokemonBaseRepository extends MongoRepository<PokemonBaseEntity, Integer>  {
    @Aggregation(pipeline = {
    		"{ '$unwind' : '$tipos' }, \n"+
    		"{'$group':{ '_id': '$tipos', 'quantidade' : {'$sum': 1} }}"
    })
    List<PokemonPorTiposEntity> groupByTipoAndCount();

//    @Aggregation(pipeline = {
//            "{'$match':{ 'tipo2': { '$in': ['BUG', 'DARK', 'DRAGON', 'ELECTRIC', 'FAIRY', 'FIGHTING', 'FIRE', 'FLYING', 'GHOST', 'GRASS', 'GROUND', 'ICE', 'NORMAL', 'POISON', 'PSYCHIC', 'ROCK', 'STEEL', 'WATER'] } }}",
//            "{'$group':{ '_id': '$tipo2', 'quantidade': {'$sum': 1} }}"
//    })
//    List<PokemonPorTiposEntity> groupByTipo2AndCount();

    List<PokemonBaseEntity> findByTipos(String tipos);
}
