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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EmitirBilheteController {
	
	@FXML Label aviso;
	@FXML Button btnCancelar;
	@FXML Button btnSalvar;
	@FXML ListView<Passageiro> listPassageiros;
	@FXML TextField poltrona;
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
		
		String cadeira = poltrona.getText();
		
		if(p != null && poltrona != null){
			
			int poltrona = Integer.parseInt(cadeira);
			Bilhete b = new Bilhete(p, flightTicket, poltrona);
			
			if(poltrona <= flightTicket.getAeronave().getCapacidade()){
				if(flightTicket.disponibilidadePoltrona(poltrona) == true){
					try{
						p.setTicketPossession(true);
						flightTicket.setPassageiro(p);
						int novaCapacidade = (flightTicket.getAeronave().getCapacidade() - 1);
						flightTicket.getAeronave().setCapacidade(novaCapacidade);
						flightTicket.setPoltrona(poltrona);
						flightTicket.setContadorBilhetes(1);
						Stage stage = (Stage) btnSalvar.getScene().getWindow();
						Fachada.getInstance().emitirBilhete(b);
						Fachada.getInstance().salvarArquivoBilhetes();
						Fachada.getInstance().salvarArquivoVoos();
						Fachada.getInstance().salvarArquivoPassageiros();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Bilhete emitido");
						alert.setHeaderText(null);
						alert.setContentText(b.toString());
						alert.showAndWait();
						stage.close();
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}else{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Operação não permitida.");
					alert.setHeaderText(null);
					alert.setContentText("Esta poltrona já está ocupada.\nPor favor, selecione outra.");
					alert.showAndWait();
				}
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Poltrona não existe");
			alert.setHeaderText(null);
			alert.setContentText("O número da poltrona extrapola capacidade do avião.\nPor favor, insira um número menor ou igual a "
					+ flightTicket.getAeronave().getCapacidade());
			alert.showAndWait();
			}
		
		}
	}

	public void setFlightTicket(Voo voo){
		this.flightTicket = voo;
	}
	
	public void voltar(){
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
	
}
