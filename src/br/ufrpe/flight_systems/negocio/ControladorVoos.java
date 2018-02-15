package br.ufrpe.flight_systems.negocio;

import java.util.ArrayList;

import br.ufrpe.flight_systems.dados.RepositorioVoos;
import br.ufrpe.flight_systems.negocio.beans.Voo;

public class ControladorVoos {
	
	private RepositorioVoos repositorio;
	private static ControladorVoos instance;
	
	//Constutor
	public ControladorVoos(){
		this.repositorio = RepositorioVoos.getInstance();
	}
	
	//Singleton
	public static ControladorVoos getInstance(){
		if(instance == null){
			instance = new ControladorVoos();
		}
		
		return instance;
	}
	
	public void adicionar(Voo voo){
		if(voo == null){
			throw new IllegalArgumentException("Entrada inv�lida");
		}else{
			repositorio.adicionar(voo);
		}
	}
	
	public ArrayList<Voo> listar(){
		return repositorio.listar();
	}
	
	public void editar(Voo voo) {
		if(voo == null){
			throw new IllegalArgumentException("Entrada inv�lida");
		}
		else{
			repositorio.editar(voo);
		}
	}
	
	public void remover(Voo voo) {
		if(voo == null){
			throw new IllegalArgumentException("Entrada inv�lida");
		}else{
			repositorio.remover(voo);
		}
	}
	
	public void salvarArquivo(){
		repositorio.salvarArquivo();
	}
}