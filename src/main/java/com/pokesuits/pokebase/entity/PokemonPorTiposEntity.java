package com.pokesuits.pokebase.entity;

import com.pokesuits.pokebase.enums.TipoPokemon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("pokemon_base")
public class PokemonPorTiposEntity {

    @Id
    private String id;
    private Integer quantidade;
}
