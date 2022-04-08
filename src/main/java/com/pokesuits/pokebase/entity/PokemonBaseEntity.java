package com.pokesuits.pokebase.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.pokesuits.pokebase.enums.Dificuldades;
import com.pokesuits.pokebase.enums.Raridades;
import com.pokesuits.pokebase.enums.TipoPokemon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("pokemon_base")
public class PokemonBaseEntity {

    @Id
    private Integer id;
    private String racaPokemon;
    private Double pesoMinimo;
    private Double pesoMaximo;
    private Double porcentagemMacho;
    private Integer levelMinimo;
    private Dificuldades dificuldade;
    private TipoPokemon tipo1;
    private TipoPokemon tipo2;
    private Raridades raridade;
}
