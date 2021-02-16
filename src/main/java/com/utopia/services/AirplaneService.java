package com.utopia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utopia.exeptions.AirportDoesNotExistException;
import com.utopia.exeptions.AirplaneNotFoundException;
import com.utopia.exeptions.AirplaneTypeDoesNotExistException;
import com.utopia.models.Airplane;
import com.utopia.models.AirplaneType;
import com.utopia.models.Airport;
import com.utopia.repositories.AirplaneRepository;

@Service
public class AirplaneService {
	
	@Autowired
	private AirplaneRepository airplaneRepository;

	public List<Airplane> findAllAirplanes() {
		return airplaneRepository.findAll();
	}
	
	public Airplane findAirplaneById(Integer id) throws AirplaneNotFoundException {
		Optional<Airplane> optionalAirplane = airplaneRepository.findById(id);
		if(!optionalAirplane.isPresent()) throw new AirplaneNotFoundException("No airplane with the id: " + id + " exists!");
		return optionalAirplane.get();
	}
	
	public List<Airplane> findAirplanesByIataId(String iata) throws AirportDoesNotExistException {
		String formattedIataId = formatIataId(iata);
		if(!validateIataId(formattedIataId)) throw new IllegalArgumentException("Not a valid IATA code: " + formattedIataId + "!");
			Optional<Airport> optionalAirpot = Optional.ofNullable(airplaneRepository.findAirportById(formattedIataId));
		if(!optionalAirpot.isPresent()) throw new AirportDoesNotExistException("No airport with IATA code: " + formattedIataId + " exist!");
		return airplaneRepository.findAirplanesByIataId(iata);
	}
	
	public List<Airplane> findAirplanesByTypeId(Integer typeId) throws AirplaneTypeDoesNotExistException {
		Optional<AirplaneType> optionalAirplaneType = Optional.ofNullable(airplaneRepository.findAirplaneTypeById(typeId));
		if(!optionalAirplaneType.isPresent()) throw new AirplaneTypeDoesNotExistException("No airplane type with id: " + typeId + " exist!");
		return airplaneRepository.findAirplanesByTypeId(typeId);
	}

	public Airplane insert(Airplane airplane) throws AirportDoesNotExistException, AirplaneTypeDoesNotExistException {
		String formattedIataId = formatIataId(airplane.getLocation().getAirportCode());
		if(!validateIataId(formattedIataId)) throw new IllegalArgumentException("Not a valid IATA code: " + formattedIataId + "!");
			Optional<Airport> optionalIata = Optional.ofNullable(airplaneRepository.findAirportById(formattedIataId));
			Optional<AirplaneType> optionalType = Optional.ofNullable(airplaneRepository.findAirplaneTypeById(airplane.getType().getId()));
		if(!optionalIata.isPresent()) throw new AirportDoesNotExistException("No airport with IATA code: " + formattedIataId + " exist!");
		if(!optionalType.isPresent()) throw new AirplaneTypeDoesNotExistException("No airplane type with id: " + airplane.getType().getId() + " exist!");
		
		airplane.setLocation(optionalIata.get());
		airplane.setType(optionalType.get());
		return airplaneRepository.save(airplane);
	}

	public void deleteById(Integer id) throws AirplaneNotFoundException {
		Optional<Airplane> optionalAirplane = airplaneRepository.findById(id);
		if(!optionalAirplane.isPresent()) throw new AirplaneNotFoundException("No airplane with the id: " + id + " exists!");
		airplaneRepository.deleteById(id);
	}

	public Airplane update(Airplane airplane) throws AirportDoesNotExistException, AirplaneNotFoundException, AirplaneTypeDoesNotExistException {
		Optional<Airplane> optionalAirplane = airplaneRepository.findById(airplane.getId());
		if(!optionalAirplane.isPresent()) throw new AirplaneNotFoundException("No airplane with the id: " + airplane.getId() + " exists!");
		
		try {
			return insert(airplane);
		} catch (AirportDoesNotExistException err) {
			throw err;
		} catch (AirplaneTypeDoesNotExistException err) {
			throw err;
		}
	}
	
	private String formatIataId(String iataId) {
		return iataId != null
		? iataId.toUpperCase().replaceAll("[^A-Z]", "")
		: null;
	}
	
	private Boolean validateIataId(String iataId) {
		return iataId != null
			&& iataId.replaceAll("[^A-Z]", "").length() == 3;
	}

}