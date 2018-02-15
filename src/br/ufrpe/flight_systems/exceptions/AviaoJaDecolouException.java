package br.ufrpe.flight_systems.exceptions;

public class AviaoJaDecolouException extends Exception{

	private static final long serialVersionUID = -1891129578516405909L;
	
	public AviaoJaDecolouException(){
		super("Esse avião já decolou.");
	}
}
