package br.ufrpe.flight_systems.dados;

import java.util.ArrayList;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;

public class RepositorioPassageiros {
	
	protected ArrayList<Passageiro> passageiros;
	private static RepositorioPassageiros instance;
	
	//Construtor
	public RepositorioPassageiros(){
		this.passageiros = new ArrayList<>();
	}
	
	//Singleton
	public static RepositorioPassageiros getInstance(){
		if(instance == null){
			instance = new RepositorioPassageiros();
		}
		
		return instance;
	}
	
	//Métodos de CRUD
	public void adicionar(Passageiro passageiro){
		if(!this.passageiros.contains(passageiro)){
			this.passageiros.add(passageiro);
		}
	}
	
	public ArrayList<Passageiro> listar(){
		return this.passageiros;
	}
	
	public void editar(Passageiro passageiro){
		int indice = buscarIndice(passageiro.getPassaporte());
		
		if(indice >= 0){
			this.passageiros.set(indice, passageiro);
		}
	}
	
	public void remover(Passageiro passageiro){
		int indice = buscarIndice(passageiro.getPassaporte());
		
		if(indice >= 0){
			this.passageiros.remove(indice);
		}
	}
	
	//Método auxiliar para buscar índice
	public int buscarIndice(String passaporte){
		int i = -1;
		
		for(int j = 0; j < passageiros.size(); j++){
			if(passageiros.get(j).getPassaporte().equals(passaporte)){
				i = j;
			}
		}
		
		return i;
	}
}
