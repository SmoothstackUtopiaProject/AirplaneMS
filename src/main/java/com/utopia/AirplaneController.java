package com.utopia;

import java.util.List;

import com.utopia.exeptions.AirportDoesNotExistException;
import com.utopia.models.Airplane;
import com.utopia.exeptions.AirplaneNotFoundException;
import com.utopia.exeptions.AirplaneTypeDoesNotExistException;
import com.utopia.services.AirplaneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/airplanes", produces = { "application/json", "application/xml", "text/xml"}, consumes = MediaType.ALL_VALUE)
public class AirplaneController {
	
	@Autowired
	AirplaneService airplaneService;
	
	@GetMapping
	public ResponseEntity<Object> findAllAirplanes(){
		List<Airplane> airplaneList = airplaneService.findAllAirplanes();
		if(airplaneList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<>(airplaneList, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Object> findAirplaneById(@RequestParam Integer id){
		try {
			Airplane airplane = airplaneService.findAirplaneById(id);
			return new ResponseEntity<>(airplane, HttpStatus.OK);
		} catch (AirplaneNotFoundException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);	
		} catch(IllegalArgumentException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/type")
	public ResponseEntity<Object> findAirplanesByTypeId(@RequestParam Integer id){
		try {
			List<Airplane> airplane = airplaneService.findAirplanesByTypeId(id);
			return new ResponseEntity<>(airplane, HttpStatus.OK);
		} catch (AirplaneTypeDoesNotExistException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);	
		} catch(IllegalArgumentException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/iata")
	public ResponseEntity<Object> findAirplanesByIataId(@RequestParam String iata_id){
		try {
			List<Airplane> airplaneList = airplaneService.findAirplanesByIataId(iata_id);
			return new ResponseEntity<>(airplaneList, HttpStatus.OK);
		} catch (AirportDoesNotExistException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);	
		} catch(IllegalArgumentException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody Airplane airplane) {
		try {
			Airplane newAirplane = airplaneService.insert(airplane);
			return new ResponseEntity<>(newAirplane, HttpStatus.CREATED);
		}	catch (AirportDoesNotExistException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
		} 	catch(IllegalArgumentException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (AirplaneTypeDoesNotExistException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping
	public ResponseEntity<Object> delete(@RequestParam Integer id) {
		try {
			airplaneService.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} catch(AirplaneNotFoundException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> update(@RequestBody Airplane airplane) {
		try {
			Airplane newAirplane = airplaneService.update(airplane);
			return new ResponseEntity<>(newAirplane, HttpStatus.OK);
		}	catch (AirportDoesNotExistException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
		}  	catch (AirplaneNotFoundException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.NOT_FOUND);
		} catch (AirplaneTypeDoesNotExistException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Object> invalidRequestContent() {
		return new ResponseEntity<>("Invalid Message Content!", HttpStatus.BAD_REQUEST);
	}
	
}