package br.ufrpe.flight_systems.negocio.beans;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Voo implements Serializable{
	
	private static final long serialVersionUID = -5470969907347820222L;
	private long id;
	private Cidade cidadeOrigem;
	private Cidade cidadeDestino;
	private ZonedDateTime horaSaida;
	private ZonedDateTime horaEstimadaChegada;
	private Aeronave aeronave;
	private ArrayList<Passageiro> passageiros;
	private boolean[] assentos;
	private int contadorBilhetes;
	//private int indice;
			
 	//Construtor
	public Voo(long id, Cidade srcCity, Cidade dstCity,	ZonedDateTime take_offTime,
			ZonedDateTime estimatedLanding, Aeronave plane){
		this.id = id;
		this.cidadeOrigem = srcCity;
		this.cidadeDestino = dstCity;
		this.horaSaida = take_offTime;
		this.horaEstimadaChegada = estimatedLanding;
		this.aeronave = plane;
		this.passageiros = new ArrayList<Passageiro>();
		this.assentos = new boolean[aeronave.getCapacidade()];
		this.contadorBilhetes = 0;
	}
	
	//M�todos Getters e Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cidade getCidadeOrigem() {
		return cidadeOrigem;
	}

	public void setCidadeOrigem(Cidade cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}

	public Cidade getCidadeDestino() {
		return cidadeDestino;
	}

	public void setCidadeDestino(Cidade cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public ZonedDateTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(ZonedDateTime horaSaida) {
		this.horaSaida = horaSaida;
	}

	public ZonedDateTime getHoraEstimadaChegada() {
		return horaEstimadaChegada;
	}

	public void setHoraEstimadaChegada(ZonedDateTime horaEstimadaChegada) {
		this.horaEstimadaChegada = horaEstimadaChegada;
	}

	public Aeronave getAeronave() {
		return aeronave;
	}

	public void setAeronave(Aeronave aeronave) {
		this.aeronave = aeronave;
	}
	
	public ArrayList<Passageiro> getPassageiros(){
		return this.passageiros;
	}
	
	public String getPassageirosVoo(){
		String texto = null;
		
		for(int i = 0; i < passageiros.size(); i++){
			texto = passageiros.get(i).toString();
		}
		
		return texto;
	}
	
	public void setPassageiro(Passageiro passageiro){
		this.passageiros.add(passageiro);
	}
	
	public void setPoltrona(int poltrona){
		if(this.assentos != null){
			this.assentos[poltrona - 1] = true;
		}
	}
	
	public boolean disponibilidadePoltrona(int poltrona){
		boolean disponivel = true;
		
		if(this.assentos != null){
			if(this.assentos[poltrona - 1] == true){
				disponivel = false;
			}
		}
		
		return disponivel;
	}
	
	public int getContadorBilhetes(){
		return this.contadorBilhetes; 
	}
	
	public void setContadorBilhetes(int contador){
		this.contadorBilhetes = (contadorBilhetes + contador);
	}
	
	//M�todo toString
	@Override
	public String toString(){
		String texto = "ID: " + this.getId();
		texto += "\nCidade origem: " + this.getCidadeOrigem().getCidade();
		texto += "\nCidade destino: " + this.getCidadeDestino().getCidade();
		texto += "\nHora de sa�da: " + this.getHoraSaida();
		texto += "\nHora estimada de chegada: " + this.getHoraEstimadaChegada();
		texto += "\nModelo da aeronave: " + this.getAeronave().getModeloAeronave();
		return texto;
	}
	
	//M�todo equals
	public boolean equals(Voo voo){
		boolean resultado = false;
		
		if(voo != null && this.id != 0 && this.aeronave != null){
			resultado = (this.id == voo.id && this.aeronave.equals(voo.getAeronave()));
		}
		
		return resultado;
	}
	
}

