package br.ufrpe.flight_systems.exceptions;

public class PoltronaNaoExisteException extends Exception{
	
	private static final long serialVersionUID = 7053297886062385603L;

	public PoltronaNaoExisteException(){
		super("O n�mero da poltrona � maior que a capacidade do avi�o.");
	}
}
