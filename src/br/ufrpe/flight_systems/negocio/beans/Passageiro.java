package br.ufrpe.flight_systems.negocio.beans;

import java.io.Serializable;

public class Passageiro implements Serializable{

	private static final long serialVersionUID = -7472324597120372549L;
	private String primeiroNome;
	private String ultimoNome;
	private String cpf;
	private String passaporte;
	
	//Constutor
	public Passageiro(String pNome, String uNome, String cpf, String passaporte){
		this.primeiroNome = pNome;
		this.ultimoNome = uNome;
		this.cpf = cpf;
		this.passaporte = passaporte;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	//Métodos Getters e Setters
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
	
	//Método toString
	@Override
	public String toString(){
		String texto = "Primeiro nome: " + this.getPrimeiroNome();
		texto += "\nÚltimo nome: " + this.getUltimoNome();
		texto += "\nCPF: " + this.getCpf();
		texto += "\nPassaporte: " + this.getPassaporte();
		return texto;
	}
	
	//Método equals
	public boolean equals(Passageiro passageiro){
		boolean resultado = false;
		
		if(passageiro != null && this.cpf != null && this.passaporte != null){
			resultado = (this.cpf.equals(passageiro.getCpf())
					&& this.passaporte.equals(passageiro.getPassaporte()));
		}
		
		return resultado;
	}
	
	
}
