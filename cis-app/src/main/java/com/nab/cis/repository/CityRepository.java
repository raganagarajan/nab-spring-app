package com.nab.cis.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nab.cis.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
	Optional<City> findOneByNameIgnoreCase(String name);
	
	@Query("SELECT c FROM City c WHERE LOWER(c.state.name) = LOWER(:stateName)")
    Stream<City> findByState(@Param("stateName") String name);

}
