package com.utopia.repositories;

import java.util.List;

import com.utopia.models.Airplane;
import com.utopia.models.AirplaneType;
import com.utopia.models.Airport;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {

	@Query(value = "SELECT * FROM airplane WHERE iata_id = ?1", nativeQuery = true)
	List<Airplane> findAirplanesByIataId(String IataId);
	
	@Query(value = "SELECT * FROM airplane WHERE type_id = ?1", nativeQuery = true)
	List<Airplane> findAirplanesByTypeId(Integer typeId);
	
	@Query(value = "SELECT * FROM airport WHERE iata_id = ?1", nativeQuery = true)
	Airport findAirportById(String id);
	
	@Query(value = "SELECT * FROM airplane_type WHERE id = ?1", nativeQuery = true)
	AirplaneType findAirplaneTypeById(Integer id);
}