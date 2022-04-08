package com.pokesuits.pokebase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pokemon_base")
public class PokemonBase {

    @Id
    private String id;
}
