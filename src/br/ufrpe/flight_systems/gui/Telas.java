package br.ufrpe.flight_systems.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class Telas {
	BorderPane paneMenu;
	BorderPane border;
	BorderPane paneAdicionarPassageiro, paneAtualizarPassageiro;
	
	FXMLLoader adicionarPassageiro, atualizarPassageiro;
	
	public static Telas instance;
	
	public static synchronized Telas getInstance() throws IOException{
		if(instance == null){
			instance = new Telas();
		}
		return instance;
	}
	
	private Telas() throws IOException{
		try{
			adicionarPassageiro = new FXMLLoader(this.getClass().getResource("AdicionarPassageiro.fxml"));
			this.paneAdicionarPassageiro = adicionarPassageiro.load();
		}catch(Exception e){

		}
	}
	
	public void getAdicionarPassageiro(){
		border.setCenter(this.paneAdicionarPassageiro);
	}
}
