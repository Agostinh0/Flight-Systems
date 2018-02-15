package br.ufrpe.flight_systems.negocio;

import java.util.ArrayList;

import br.ufrpe.flight_systems.dados.RepositorioPassageiros;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;

public class ControladorPassageiros {
	
	private RepositorioPassageiros repositorio;
	private static ControladorPassageiros instance;
	
	//Construtor
	public ControladorPassageiros(){
		this.repositorio = RepositorioPassageiros.getInstance();
	}
	
	//Singleton
	public static ControladorPassageiros getInstance(){
		if(instance == null){
			instance = new ControladorPassageiros();
		}
		
		return instance;
	}
	
	public void adicionar(Passageiro passageiro){
		if(passageiro == null){
			throw new IllegalArgumentException("Entrada inválida.");
		}else{
			repositorio.adicionar(passageiro);
		}
	}
	
	public ArrayList<Passageiro> listar(){
		return this.repositorio.listar();
	}
	
	public void editar(Passageiro passageiro){
		if(passageiro == null){
			throw new IllegalArgumentException("Entrada inválida");
		}else{
			repositorio.editar(passageiro);
		}
	}
	
	public void remover(Passageiro passageiro) {
		if(passageiro == null){
			throw new IllegalArgumentException("Entrada inválida.");
		}else{
			repositorio.remover(passageiro);
		}
	}
	
	
	public void salvarArquivo(){
		repositorio.salvarArquivo();
	}
}
