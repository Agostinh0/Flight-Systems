package br.ufrpe.flight_systems.negocio.beans;

import java.io.Serializable;

public class Passageiro implements Serializable{

	private static final long serialVersionUID = -7472324597120372549L;
	private String primeiroNome;
	private String ultimoNome;
	private String cpf;
	private String passaporte;
	private boolean emitiuBilhete = false;
	private int indice;
	
	//Constutor
	public Passageiro(String pNome, String uNome, String cpf, String passaporte){
		this.primeiroNome = pNome;
		this.ultimoNome = uNome;
		this.cpf = cpf;
		this.passaporte = passaporte;
	}
	
	//M�todos Getters e Setters
	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}
	
	public boolean hasTicket(){
		return this.emitiuBilhete;
	}
	
	public void setTicketPossession(boolean option){
		this.emitiuBilhete = option;
	}
	
	public int getIndice(){
		return this.indice;
	}
	
	//M�todo toString
	@Override
	public String toString(){
		String texto = "Primeiro nome: " + this.getPrimeiroNome();
		texto += "\n�ltimo nome: " + this.getUltimoNome();
		texto += "\nCPF: " + this.getCpf();
		texto += "\nPassaporte: " + this.getPassaporte();
		texto += "\n----------------------------\n";
		return texto;
	}
	
	//M�todo equals
	public boolean equals(Passageiro passageiro){
		boolean resultado = false;
		
		if(passageiro != null && this.cpf != null && this.passaporte != null){
			resultado = (this.cpf.equals(passageiro.getCpf())
					&& this.passaporte.equals(passageiro.getPassaporte()));
		}
		
		return resultado;
	}
	
	
}
