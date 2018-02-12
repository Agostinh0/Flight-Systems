package br.ufrpe.flight_systems.negocio;

import java.util.ArrayList;

import br.ufrpe.flight_systems.negocio.beans.Bilhete;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;
import br.ufrpe.flight_systems.negocio.beans.Voo;

public class Fachada {
	private ControladorPassageiros controladorPassageiros;
	private ControladorVoos controladorVoos;
	private ControladorBilhetes controladorBilhetes;
	private static Fachada instance;
	
	//Construtor
	public Fachada(){
		this.controladorPassageiros = ControladorPassageiros.getInstance();
		this.controladorVoos = ControladorVoos.getInstance();
		this.controladorBilhetes = ControladorBilhetes.getInstance();
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
	
	public void salvarArquivoPassageiros(){
		this.controladorPassageiros.salvarArquivo();
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
	
	public void salvarArquivoVoos(){
		this.controladorVoos.salvarArquivo();
	}
	
	//Bilhetes
	public void emitirBilhete(Bilhete bilhete){
		this.controladorBilhetes.adicionar(bilhete);
	}
	
	public ArrayList<Bilhete> listarBilhetes(){
		return this.controladorBilhetes.listar();
	}
	
	public void removerBilhete(Bilhete bilhete){
		this.controladorBilhetes.remover(bilhete);
	}
	
	public void salvarArquivoBilhetes(){
		this.controladorBilhetes.salvarArquivo();
	}
}
