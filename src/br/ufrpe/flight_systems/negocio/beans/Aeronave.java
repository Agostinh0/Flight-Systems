package br.ufrpe.flight_systems.negocio.beans;

public enum Aeronave {
	AIRBUS_A320("Airbus A320"), AIRBUS_A330("Airbus A330"), AIRBUS_A350("Airbus A350"), BOEING_757("Boeing 757"),
	BOEING_787("Boeing 787"), BOEING_737("Boeing 737");
	
	private String modeloAeronave;
	private int capacidade;
	
	//Construtor
	Aeronave(String modeloAeronave){
		this.modeloAeronave = modeloAeronave;
		switch(modeloAeronave){
			case "Airbus A320": capacidade = 220; break;
			case "Airbus A330": capacidade = 335; break;
			case "Airbus A350": capacidade = 366; break;
			case "Boeing 757": capacidade = 295; break;
			case "Boeing 787": capacidade = 400; break;
			case "Boeing 737": capacidade = 215; break;
		}
	}
	
	public String getModeloAeronave(){
		return modeloAeronave;
	}
	
	public int getCapacidade(){
		return this.capacidade;
	}
}