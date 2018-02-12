package br.ufrpe.flight_systems.dados;

import java.util.ArrayList;
import br.ufrpe.flight_systems.negocio.beans.Bilhete;

public class RepositorioBilhetes {
	
	protected ArrayList<Bilhete> bilhetes;
	private static RepositorioBilhetes instance;
	
	//Construtor
	public RepositorioBilhetes(){
		this.bilhetes = new ArrayList<>();
	}
	
	//Singleton
	public static RepositorioBilhetes getInstance(){
		if(instance == null){
			instance = new RepositorioBilhetes();
		}
		
		return instance;
	}
	
	//M�todos de CRUD (sem m�todo editar, que n�o foi requisitado)
	public void adicionar(Bilhete bilhete){
		if(!this.bilhetes.contains(bilhete)){
			this.bilhetes.add(bilhete);
		}
	}
	
	public ArrayList<Bilhete> listar(){
		return this.bilhetes;
	}
	
	public void remover(Bilhete bilhete){
		int indice = buscarIndice(bilhete.getPoltrona());
		
		if(indice >= 0){
			this.bilhetes.remove(indice);
		}
	}
	
	//M�todo auxiliar para buscar �ndice
	public int buscarIndice(int poltrona){
		int i = -1;
		
		for(int j = 0; j < bilhetes.size(); j++){
			if(bilhetes.get(j).getPoltrona() == poltrona){
				i = j;
			}
		}
		
		return i;
	}
}
