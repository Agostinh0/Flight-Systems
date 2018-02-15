package br.ufrpe.flight_systems.exceptions;

public class PoltronaOcupadaException extends Exception{
	
	private static final long serialVersionUID = 2348747617602504386L;

	public PoltronaOcupadaException(){
		super("Esta poltrona já está ocupada.");
	}
}
