package br.ufrpe.flight_systems.gui;

import java.util.ArrayList;

import br.ufrpe.flight_systems.negocio.Fachada;
import br.ufrpe.flight_systems.negocio.beans.Bilhete;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;
import br.ufrpe.flight_systems.negocio.beans.Voo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class EmitirBilheteController {
	
	@FXML Label aviso;
	@FXML Button btnCancelar;
	@FXML Button btnSalvar;
	@FXML ListView<Passageiro> listPassageiros;
	@FXML ChoiceBox<boolean[]> assentos;
	private Voo flightTicket;
	
	private ArrayList<Passageiro> lista = new ArrayList<>();
	private ObservableList<Passageiro> obsList;
	private FilteredList<Passageiro> filtdList;
	
	public void initialize(){
		lista = Fachada.getInstance().listarPassageiros();
		obsList = FXCollections.observableArrayList(lista);
		filtdList = new FilteredList<>(obsList, data -> true);
		listPassageiros.setItems(filtdList);
	}
	
	public void salvar(){
		Passageiro p = null;
		p = listPassageiros.getSelectionModel().getSelectedItem();
		
		if(p != null && assentos != null){
			
		}
	}
	
	public void setFlightTicket(Voo voo){
		this.flightTicket = voo;
	}
	
}
