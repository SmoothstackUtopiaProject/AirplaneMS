package com.utopia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport {
	@Id
	@Column(name = "iata_id")
	private String airportCode;

	@Column(name = "city")
	private String city;

	public Airport() {}

	public Airport(String airportCode, String city) {
		super();
		this.airportCode = airportCode;
		this.city = city;
	}
	
	public Airport(String airportCode) {
		super();
		this.airportCode = airportCode;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String aiportCode) {
		this.airportCode = aiportCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

}
