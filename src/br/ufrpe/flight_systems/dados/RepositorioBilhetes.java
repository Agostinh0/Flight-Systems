package br.ufrpe.flight_systems.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import br.ufrpe.flight_systems.negocio.beans.Bilhete;

public class RepositorioBilhetes implements Serializable{
	
	private static final long serialVersionUID = 753292792333386214L;
	protected ArrayList<Bilhete> bilhetes;
	private static RepositorioBilhetes instance;
	
	//Construtor
	public RepositorioBilhetes(){
		this.bilhetes = new ArrayList<>();
	}
	
	//Singleton
	public static RepositorioBilhetes getInstance(){
		if(instance == null){
			instance = RepositorioBilhetes.lerArquivo();
		}
		
		return instance;
	}
	
	//Métodos de CRUD (sem método editar, que não foi requisitado)
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
	
	//Método auxiliar para buscar índice
	public int buscarIndice(int poltrona){
		int i = -1;
		
		for(int j = 0; j < bilhetes.size(); j++){
			if(bilhetes.get(j).getPoltrona() == poltrona){
				i = j;
			}
		}
		
		return i;
	}
	
	//Arquivos
	private static RepositorioBilhetes lerArquivo(){
		RepositorioBilhetes instanciaLocal = null;
		
		File arquivo = new File("repositorioBilhetes.dat");
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try{
			
			fis = new FileInputStream(arquivo);
			ois = new ObjectInputStream(fis);
			
			Object o = ois.readObject();
			
			instanciaLocal = (RepositorioBilhetes) o;
			
		}catch(Exception e){
			instanciaLocal = new RepositorioBilhetes();
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
		
		File arquivo = new File("repositorioBilhetes.dat");
		
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
