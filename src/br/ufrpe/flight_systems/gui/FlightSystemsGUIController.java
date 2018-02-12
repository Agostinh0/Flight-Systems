package br.ufrpe.flight_systems.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufrpe.flight_systems.negocio.Fachada;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;
import br.ufrpe.flight_systems.negocio.beans.Voo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FlightSystemsGUIController implements Initializable{
	
	@FXML Label aviso;
	@FXML TableView<Voo> tabelaVoos;
	@FXML TableColumn<Voo, String> tcCidadeOrigem;
	@FXML TableColumn<Voo, String> tcCidadeDestino;
	@FXML TableColumn<Voo, LocalTime> tcHoraSaida;
	@FXML TableColumn<Voo, ZonedDateTime> tcHoraChegada;
	@FXML TableView<Passageiro> tabelaPassageiros;
	@FXML TableColumn<Passageiro, String> tcPrimeiroNome;
	@FXML TableColumn<Passageiro, String> tcUltimoNome;
	@FXML TableColumn<Passageiro, String>tcCpf;
	@FXML TableColumn<Passageiro, String> tcPassaporte; 
	@FXML private Button btnAdicionarPassageiro;
	@FXML private Button btnRemoverPassageiro;
	@FXML private Button btnAtualizarPassageiro;
	@FXML private Button btnAdicionarVoo;
	@FXML private Button btnRemoverVoo; 
	@FXML private Button btnAtualizarVoo;
	@FXML private ObservableList<Passageiro> lista;
	
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
		tcCidadeOrigem.setCellValueFactory(new PropertyValueFactory<Voo, String>("CidadeOrigem"));
		tcCidadeDestino.setCellValueFactory(new PropertyValueFactory<Voo, String>("CidadeDestino"));
		tcHoraSaida.setCellValueFactory(new PropertyValueFactory<Voo, LocalTime>("HoraSaida"));
		tcHoraChegada.setCellValueFactory(new PropertyValueFactory<Voo, ZonedDateTime>("HoraChegada"));
		
		tabelaVoos.setItems(FXCollections.observableList(Fachada.getInstance().listarVoos()));
		tabelaVoos.refresh();
	}
	
	public void tabelaPassageiros(){
		tcPrimeiroNome.setCellValueFactory(new PropertyValueFactory<Passageiro, String>("PrimeiroNome"));
		tcUltimoNome.setCellValueFactory(new PropertyValueFactory<Passageiro, String>("UltimoNome"));
		tcCpf.setCellValueFactory(new PropertyValueFactory<Passageiro, String>("Cpf"));
		tcPassaporte.setCellValueFactory(new PropertyValueFactory<Passageiro, String>("Passaporte"));
		
		tabelaPassageiros.setItems(FXCollections.observableList(Fachada.getInstance().listarPassageiros()));
		tabelaPassageiros.refresh();
	}
	
	public void openAddMenu(ActionEvent event){
		try{
			BorderPane root = FXMLLoader.load(getClass().getResource("/br/ufrpe/flight_systems/gui/AdicionarPassageiro.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Flight Systems");
			stage.show();
		}catch(IOException e){
			Logger.getLogger(FlightSystemsGUIController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void openFlightAddMenu(ActionEvent event){
		try{
			BorderPane root = FXMLLoader.load(getClass().getResource("/br/ufrpe/flight_systems/gui/AdicionarVoo.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Flight Systems");
			stage.show();
		}catch(IOException e){
			Logger.getLogger(FlightSystemsGUIController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void openEditMenu(ActionEvent event){
		Passageiro p = null;
		p = tabelaPassageiros.getSelectionModel().getSelectedItem();
		
		if(p != null){
			try{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/ufrpe/flight_systems/gui/AtualizarPassageiro.fxml"));
				BorderPane root = (BorderPane) loader.load();
				AtualizarPassageiroController attPassenger = loader.getController();
				attPassenger.setPassengerEdit(p);
				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setScene(new Scene(root));
				stage.setTitle("Flight Systems");
				stage.show();
			}catch(IOException e){
				Logger.getLogger(FlightSystemsGUIController.class.getName()).log(Level.SEVERE, null, e);
			}
		}else{
			aviso.setText("Selecione um item da lista.");
		}
	}
	
	public void openFlightEditMenu(ActionEvent event){
		Voo v = null;
		v = tabelaVoos.getSelectionModel().getSelectedItem();
		
		if(v != null){
			try{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/ufrpe/flight_systems/gui/AtualizarVoo.fxml"));
				BorderPane root = (BorderPane) loader.load();
				AtualizarVooController attFlight = loader.getController();
				attFlight.setFlightEdit(v);
				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setScene(new Scene(root));
				stage.setTitle("Flight Systems");
				stage.show();
			}catch(IOException e){
				Logger.getLogger(FlightSystemsGUIController.class.getName()).log(Level.SEVERE, null, e);
			}
		}else{
			aviso.setText("Selecione um item da lista.");
		}
	}
	
	public void deletePassenger(ActionEvent event){
		Passageiro p = null;
		p = tabelaPassageiros.getSelectionModel().getSelectedItem();
		
		if(p != null){
			try{
				//Stage stage = (Stage) btnRemoverPassageiro.getScene().getWindow();
				Fachada.getInstance().removerPassageiro(p);
				Fachada.getInstance().salvarArquivoPassageiros();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Passageiro removido.");
				alert.setHeaderText(null);
				alert.setContentText("Passageiro removido");
				alert.showAndWait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			aviso.setText("Selecione um item da lista.");
		}
	}
	
	public void deleteFlight(ActionEvent event){
		Voo v = null;
		v = tabelaVoos.getSelectionModel().getSelectedItem();
		
		if(v != null){
			try{
				Fachada.getInstance().removerVoo(v);
				Fachada.getInstance().salvarArquivoVoos();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Vôo removido.");
				alert.setHeaderText(null);
				alert.setContentText("Vôo removido.");
				alert.showAndWait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			aviso.setText("Selecione um item da lista.");
		}
	}
	
	public void openEmitirBilheteMenu(){
		Voo v = null;
		v = tabelaVoos.getSelectionModel().getSelectedItem();
		
		if(v!= null){
			try{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/ufrpe/flight_systems/gui/EmitirBilhete.fxml"));
				BorderPane root = (BorderPane) loader.load();
				EmitirBilheteController createTicket = loader.getController();
				createTicket.setFlightTicket(v);
				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setScene(new Scene(root));
				stage.setTitle("Flight Systems");
				stage.show();
			}catch(Exception e){
				
			}
		}
	}
	
	public void listarPassageirosPorVoo(){
		Voo v = null;
		v = tabelaVoos.getSelectionModel().getSelectedItem();
		
		if(v!=null){
			try{	
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Passageiros neste vôo");
				alert.setHeaderText(null);
				alert.setContentText(v.getPassageiros().toString());
				alert.showAndWait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Passageiro> listarPassageirosPorVoo(Voo voo){
		return voo.getPassageiros();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
