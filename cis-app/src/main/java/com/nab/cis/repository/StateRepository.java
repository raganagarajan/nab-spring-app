package com.nab.cis.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nab.cis.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
	
	Optional<State> findOneByNameIgnoreCase(String name);
	
	@Query("SELECT s FROM State s WHERE LOWER(s.country.name) = LOWER(:countryName)")
    Stream<State> findByCountry(@Param("countryName") String name);

}
