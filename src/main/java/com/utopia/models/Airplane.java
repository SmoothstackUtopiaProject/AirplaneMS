package com.utopia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "airplane")
public class Airplane {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private AirplaneType type;

	@ManyToOne
	@JoinColumn(name = "iata_id")
	private Airport location;

	public Airplane() {}
	
	public Airplane(Airport iata_id, AirplaneType type_id) {
		super();
		this.type = type_id;
		this.location = iata_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AirplaneType getType() {
		return type;
	}

	public void setType(AirplaneType type) {
		this.type = type;
	}

	public Airport getLocation() {
		return location;
	}

	public void setLocation(Airport location) {
		this.location = location;
	}
	
}
