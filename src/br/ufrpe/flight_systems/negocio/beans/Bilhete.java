package br.ufrpe.flight_systems.negocio.beans;

public class Bilhete {
	
	private Passageiro passageiro;
	private Voo voo;
	private int poltrona;
	
	//Construtor
	public Bilhete(Passageiro passenger, Voo flight, int seat){
		this.passageiro = passenger;
		this.voo = flight;
		this.poltrona = seat;
	}

	//Métodos Getters e Setters
	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}
	
	public int getPoltrona(){
		return poltrona;
	}
	
	public void setPoltrona(int poltrona){
		this.poltrona = poltrona;
	}
	//Método toString
	public String toString(){
		String texto = "--------BILHETE--------";
		texto += "\nPoltrona: " + this.getPoltrona();
		texto += "\nPassageiro--------\n";
		texto += this.getPassageiro();
		texto += "\nVôo------------\n";
		texto += this.getVoo();
		return texto;
		
	}
}
