package br.ufrpe.flight_systems.negocio;

import java.util.ArrayList;

import br.ufrpe.flight_systems.dados.RepositorioBilhetes;
import br.ufrpe.flight_systems.negocio.beans.Bilhete;;

public class ControladorBilhetes {
	
	private RepositorioBilhetes repositorio;
	private static ControladorBilhetes instance;
	//Construtor
	public ControladorBilhetes(){
		this.repositorio = RepositorioBilhetes.getInstance();
	}
	
	//Singleton
	public static ControladorBilhetes getInstance(){
		if(instance == null){
			instance = new ControladorBilhetes();
		}
		
		return instance;
	}
	
	public void adicionar(Bilhete bilhete){
		if(bilhete == null){
			throw new IllegalArgumentException("Entrada inválida.");
		}else{
			repositorio.adicionar(bilhete);
		}
	}
	
	public ArrayList<Bilhete> listar(){
		return repositorio.listar();
	}
	
	public void remover(Bilhete bilhete){
		if(bilhete == null){
			throw new IllegalArgumentException("Entrada inválida.");
		}else{
			repositorio.remover(bilhete);
		}
	}
	
	public void salvarArquivo(){
		repositorio.salvarArquivo();
	}
}
