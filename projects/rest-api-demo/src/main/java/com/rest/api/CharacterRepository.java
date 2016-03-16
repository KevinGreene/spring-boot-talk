package com.rest.api;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CharacterRepository extends PagingAndSortingRepository<Character, Long> {
    List<Character> findByLastName(@Param("name") String name);

}
