package com.utopia.exeptions;

public class AirplaneTypeDoesNotExistException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public AirplaneTypeDoesNotExistException() {};
	
	public AirplaneTypeDoesNotExistException(String message) {
		super(message);
	}
	

}
