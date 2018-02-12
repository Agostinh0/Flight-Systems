package br.ufrpe.flight_systems.dados;

import java.util.ArrayList;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;
import br.ufrpe.flight_systems.negocio.beans.Voo;

public class RepositorioVoos {
	
	protected ArrayList<Voo> voos;
	private static RepositorioVoos instance;
	
	//Construtor
	public RepositorioVoos(){
		this.voos = new ArrayList<>();
	}
	
	//Singleton
	public static RepositorioVoos getInstance(){
		if(instance == null){
			instance = new RepositorioVoos();
		}
		
		return instance;
	}
	
	//Métodos de CRUD
	public void adicionar(Voo voo){
		if(!this.voos.contains(voo)){
			this.voos.add(voo);
		}
	}
	
	public ArrayList<Voo> listar(){
		return this.voos;
	}
	
	public void editar(Voo voo){
		int indice = buscarIndice(voo.getId());
		
		if(indice >= 0){
			this.voos.set(indice, voo);
		}
	}
	
	public void remover(Voo voo){
		int indice = buscarIndice(voo.getId());
		
		if(indice >= 0){
			this.voos.remove(indice);
		}
	}
	
	//Método auxiliar para buscar índice
	public int buscarIndice(long id){
		int i = -1;
		
		for(int j = 0; j<voos.size(); j++){
			if(this.voos.get(j).getId() == id){
				i = j;
			}
		}
		return i;
	}
	
	//Adicionar pessoas a um vôo
	public void adicionarPassageiroAoVoo(Passageiro passageiro, long id){
		int indice = buscarIndice(id);
		voos.get(indice).setPassageiro(passageiro);
	}
}
