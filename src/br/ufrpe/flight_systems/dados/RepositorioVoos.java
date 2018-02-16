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
import br.ufrpe.flight_systems.negocio.beans.Voo;

public class RepositorioVoos implements Serializable{
	
	private static final long serialVersionUID = 1653429873753232989L;
	protected ArrayList<Voo> voos;
	private static RepositorioVoos instance;
	
	//Construtor
	public RepositorioVoos(){
		this.voos = new ArrayList<>();
	}
	
	//Singleton
	public static RepositorioVoos getInstance(){
		if(instance == null){
			instance = RepositorioVoos.lerArquivo();
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
	
	public String listarPassageirosNoVoo(Voo voo){
		return voo.getPassageirosVoo();
	}
	
	//Arquivos
	private static RepositorioVoos lerArquivo(){
		RepositorioVoos instanciaLocal = null;
		
		File arquivo = new File("repositorioVoos.dat");
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try{
			
			fis = new FileInputStream(arquivo);
			ois = new ObjectInputStream(fis);
			
			Object o = ois.readObject();
			
			instanciaLocal = (RepositorioVoos) o;
			
		}catch(Exception e){
			instanciaLocal = new RepositorioVoos();
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
		
		File arquivo = new File("repositorioVoos.dat");
		
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
