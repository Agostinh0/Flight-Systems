package br.ufrpe.flight_systems.gui;

import java.util.ArrayList;

import br.ufrpe.flight_systems.negocio.Fachada;
import br.ufrpe.flight_systems.negocio.beans.Bilhete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BilhetesEmitidosController {
	
	@FXML Label aviso;
	@FXML Button btnVoltar;
	@FXML Button btnRemover;
	@FXML ListView<Bilhete> listBilhetes;
	
	private ArrayList<Bilhete> lista = new ArrayList<>();
	private ObservableList<Bilhete> obsList;
	private FilteredList<Bilhete> filtdList;
	
	public void initialize(){
		lista = Fachada.getInstance().listarBilhetes();
		obsList = FXCollections.observableArrayList(lista);
		filtdList = new FilteredList<>(obsList, data -> true);
		listBilhetes.setItems(filtdList);
	}
	
	public void removerBilhete(){
		Bilhete b = null;
		b = listBilhetes.getSelectionModel().getSelectedItem();
		
		if(b != null){
			try{
				Fachada.getInstance().removerBilhete(b);
				Fachada.getInstance().salvarArquivoBilhetes();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Bilhete removido.");
				alert.setHeaderText(null);
				alert.setContentText("Bilhete removido.");
				alert.showAndWait();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void voltar(){
		Stage stage = (Stage) btnVoltar.getScene().getWindow();
		stage.close();
	}
}
