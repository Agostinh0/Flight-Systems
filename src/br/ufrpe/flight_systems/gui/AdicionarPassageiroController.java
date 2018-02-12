package br.ufrpe.flight_systems.gui;

import br.ufrpe.flight_systems.negocio.Fachada;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdicionarPassageiroController {
	
	private Fachada fachada = Fachada.getInstance();
	
	@FXML Label aviso;
	@FXML Button btnCancelar;
	@FXML Button btnSalvar;
	@FXML TextField pNome;
	@FXML TextField uNome;
	@FXML TextField cpf;
	@FXML TextField passaporte;
	
	public void salvar(){
		
		String firstName, lastName, cpfPassenger, passport;
		firstName = pNome.getText();
		lastName = uNome.getText();
		cpfPassenger = cpf.getText();
		passport = passaporte.getText();
		
		Passageiro p = new Passageiro(firstName, lastName, cpfPassenger, passport);
		
		try{
			Stage stage = (Stage) btnSalvar.getScene().getWindow();
			fachada.adicionarPassageiro(p);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Passageiro adicionado!");
			alert.setHeaderText(null);
			alert.setContentText(p.toString());
			alert.showAndWait();
			stage.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void voltar(){
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
	
}
