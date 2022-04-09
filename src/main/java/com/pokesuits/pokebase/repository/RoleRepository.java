package com.pokesuits.pokebase.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pokesuits.pokebase.entity.RoleEntity;

@Repository
public interface RoleRepository extends MongoRepository<RoleEntity, String> {}
