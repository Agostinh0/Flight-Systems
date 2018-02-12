package br.ufrpe.flight_systems.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;

public class RepositorioPassageiros implements Serializable{
	
	private static final long serialVersionUID = 5188081318345668508L;
	protected ArrayList<Passageiro> passageiros;
	private static RepositorioPassageiros instance;
	
	//Construtor
	public RepositorioPassageiros(){
		this.passageiros = new ArrayList<>();
	}
	
	//Singleton
	public static RepositorioPassageiros getInstance(){
		if(instance == null){
			instance = RepositorioPassageiros.lerArquivo();
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
			this.passageiros.get(indice).setPrimeiroNome(passageiro.getPrimeiroNome());
			this.passageiros.get(indice).setUltimoNome(passageiro.getUltimoNome());
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
	
	//Arquivos
	private static RepositorioPassageiros lerArquivo(){
		RepositorioPassageiros instanciaLocal = null;
		
		File arquivo = new File("repositorioPassageiros.dat");
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try{
			
			fis = new FileInputStream(arquivo);
			ois = new ObjectInputStream(fis);
			
			Object o = ois.readObject();
			
			instanciaLocal = (RepositorioPassageiros) o;
			
		}catch(Exception e){
			instanciaLocal = new RepositorioPassageiros();
		}finally{
			if(ois != null){
				try{
					ois.close();
				}catch(IOException e){
					
				}
			}
		}
		
		return instanciaLocal;
	}
	
	public void salvarArquivo(){
		if(instance == null){
			return;
		}
		
		File arquivo = new File("repositorioPassageiros.dat");
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try{
			if(!arquivo.exists()){
				arquivo.createNewFile();
			}
			
			fos = new FileOutputStream(arquivo);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(oos != null){
				try{
					oos.close();
				}catch(IOException e){
					
				}
			}
		}
		
	}
}
