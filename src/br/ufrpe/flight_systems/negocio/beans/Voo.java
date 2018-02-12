package br.ufrpe.flight_systems.negocio.beans;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Voo {
	
	private long id;
	private Cidade cidadeOrigem;
	private Cidade cidadeDestino;
	private LocalDateTime horaSaida;
	private ZonedDateTime horaEstimadaChegada;
	private Aeronave aeronave;
	private ArrayList<Passageiro> passageiros;
	
	private boolean[] assentos; 
			
 	//Construtor
	public Voo(long id, Cidade srcCity, Cidade dstCity, LocalDateTime take_offTime,
			ZonedDateTime estimatedLanding, Aeronave plane){
		this.id = id;
		this.cidadeOrigem = srcCity;
		this.cidadeDestino = dstCity;
		this.horaSaida = take_offTime;
		this.horaEstimadaChegada = estimatedLanding;
		this.aeronave = plane;
		this.passageiros = new ArrayList<Passageiro>();
		this.assentos = new boolean[aeronave.getCapacidade()];
	}
	
	//Métodos Getters e Setters
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

	public LocalDateTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(LocalDateTime horaSaida) {
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
	
	public void setPassageiro(Passageiro passageiro){
		this.passageiros.add(passageiro);
	}
	
	//Método toString
	@Override
	public String toString(){
		String texto = "ID: " + this.getId();
		texto += "\nCidade origem: " + this.getCidadeOrigem();
		texto += "\nCidade destino: " + this.getCidadeDestino();
		texto += "\nHora de saída: " + this.getHoraSaida();
		texto += "\nHora estimada de chegada: " + this.getHoraEstimadaChegada();
		texto += "\nModelo da aeronave: " + this.getAeronave();
		texto += "\nPassageiros no vôo: " + this.passageiros.toString();
		return texto;
	}
	
	//Método equals
	public boolean equals(Voo voo){
		boolean resultado = false;
		
		if(voo != null && this.id != 0 && this.aeronave != null){
			resultado = (this.id == voo.id && this.aeronave.equals(voo.getAeronave()));
		}
		
		return resultado;
	}
	
}

