package br.ufrpe.flight_systems.negocio;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import br.ufrpe.flight_systems.dados.RepositorioVoos;
import br.ufrpe.flight_systems.exceptions.ExisteBilheteException;
import br.ufrpe.flight_systems.exceptions.VooJaRealizadoException;
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
			throw new IllegalArgumentException("Entrada inválida");
		}else{
			repositorio.adicionar(voo);
		}
	}
	
	public ArrayList<Voo> listar(){
		return repositorio.listar();
	}
	
	public void editar(Voo voo) {
		if(voo == null){
			throw new IllegalArgumentException("Entrada inválida");
		}else{
			repositorio.editar(voo);
		}
	}
	
	public void remover(Voo voo) throws VooJaRealizadoException, ExisteBilheteException {
		if(voo == null){
			throw new IllegalArgumentException("Entrada inválida");
		}else if(voo.getHoraSaida().isBefore(ZonedDateTime.now()) && voo.getHoraEstimadaChegada().isBefore(ZonedDateTime.now())){
			throw new VooJaRealizadoException();
		}else if(voo.getContadorBilhetes() > 0){
			throw new ExisteBilheteException();
		}
		else{
			repositorio.remover(voo);
		}
	}
	
	public String listarPassageirosNoVoo(Voo voo){
		return repositorio.listarPassageirosNoVoo(voo);
	}
	
	public void salvarArquivo(){
		repositorio.salvarArquivo();
	}
}