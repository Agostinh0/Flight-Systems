package br.ufrpe.flight_systems.negocio;

import java.util.ArrayList;

import br.ufrpe.flight_systems.negocio.beans.Passageiro;
import br.ufrpe.flight_systems.negocio.beans.Voo;

public class Fachada {
	private ControladorPassageiros controladorPassageiros;
	private ControladorVoos controladorVoos;
	private static Fachada instance;
	
	//Construtor
	public Fachada(){
		this.controladorPassageiros = ControladorPassageiros.getInstance();
		this.controladorVoos = ControladorVoos.getInstance();
	}
	
	//Singleton
	public static Fachada getInstance(){
		if(instance == null){
			instance = new Fachada();
		}
		
		return instance;
	}
	
	//Passageiros
	public void adicionarPassageiro(Passageiro passageiro){
		this.controladorPassageiros.adicionar(passageiro);
	}
	
	public ArrayList<Passageiro> listarPassageiros(){
		return this.controladorPassageiros.listar();
	}
	
	public void editarPassageiro(Passageiro passageiro){
		this.controladorPassageiros.editar(passageiro);
	}
	
	public void removerPassageiro(Passageiro passageiro){
		this.controladorPassageiros.remover(passageiro);
	}
	
	//Voos
	public void adicionarVoo(Voo voo){
		this.controladorVoos.adicionar(voo);
	}
	
	public ArrayList<Voo> listarVoos(){
		return this.controladorVoos.listar();
	}
	
	public void editarVoo(Voo voo){
		this.controladorVoos.editar(voo);
	}
	
	public void removerVoo(Voo voo){
		this.controladorVoos.remover(voo);
	}
	
}
