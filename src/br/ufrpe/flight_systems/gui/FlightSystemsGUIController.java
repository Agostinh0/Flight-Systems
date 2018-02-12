package br.ufrpe.flight_systems.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufrpe.flight_systems.negocio.Fachada;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;
import br.ufrpe.flight_systems.negocio.beans.Voo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class FlightSystemsGUIController implements Initializable{
	
	@FXML 
	TableView<Voo> tabelaVoos;
	@FXML 
	TableColumn<Voo, String> tcCidadeOrigem, tcCidadeDestino, tcHoraSaida, tcHoraChegada;
	@FXML 
	TableView<Passageiro> tabelaPassageiros;
	@FXML 
	TableColumn<Passageiro, String> tcPrimeiroNome, tcUltimoNome, tcCpf, tcPassaporte; 
	@FXML 
	private BorderPane mainScreen;
	@FXML
	private Button btnAdicionarPassageiro, btnRemoverPassageiro, btnAtualizarPassageiro, btnAdicionarVoo, btnRemoverVoo, 
					btnAtualizarVoo;
	
	public void initializeVoos() {
		tabelaVoos.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event){
				tabelaVoos.getSelectionModel().getSelectedItem();
				
			}
		});
		
	}
	
	public void initializePassageiros(){
		tabelaPassageiros.setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent event){
				tabelaPassageiros.getSelectionModel().getSelectedItem();
			}
		});
	}
	
	public void optionsButtons(ActionEvent event){
		if(((Button) event.getSource()).getId().equals(btnAdicionarPassageiro)){
			openAddMenu(event);
		}
	}

	public void tabelaVoos(){
		tcCidadeOrigem.setCellValueFactory(new PropertyValueFactory<Voo, String>("Cidade origem"));
		tcCidadeDestino.setCellValueFactory(new PropertyValueFactory<Voo, String>("Cidade destino"));
		//tcHoraSaida.setCellValueFactory(new PropertyValueFactory<Voo, Integer>("Hora saída"));
		//tcHoraChegada.setCellValueFactory(new PropertyValueFactory<Voo, Integer>("Hora estimada de chegada"));
		
		tabelaVoos.setItems(FXCollections.observableList(Fachada.getInstance().listarVoos()));
		tabelaVoos.refresh();
	}
	
	public void tabelaPassageiros(){
		tcPrimeiroNome.setCellValueFactory(new PropertyValueFactory<Passageiro, String>("Primeiro nome"));
		tcUltimoNome.setCellValueFactory(new PropertyValueFactory<Passageiro, String>("Último nome"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<Passageiro, String>("CPF"));
		tcPassaporte.setCellValueFactory(new PropertyValueFactory<Passageiro, String>("Passaporte"));
		
		tabelaPassageiros.setItems(FXCollections.observableList(Fachada.getInstance().listarPassageiros()));
		tabelaPassageiros.refresh();
	}
	
	public void openAddMenu(ActionEvent event){
		try{
			BorderPane root = FXMLLoader.load(getClass().getResource("/br/ufrpe/flight_systems/gui/AdicionarPassageiros.fxml"));
			mainScreen.setCenter(root);
		}catch(IOException e){
			Logger.getLogger(FlightSystemsGUIController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
