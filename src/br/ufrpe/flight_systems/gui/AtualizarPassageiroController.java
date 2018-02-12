package br.ufrpe.flight_systems.gui;

import br.ufrpe.flight_systems.negocio.Fachada;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AtualizarPassageiroController {
	
	@FXML Label aviso;
	@FXML Button btnCancelar;
	@FXML Button btnSalvar;
	@FXML TextField newFirstName;
	@FXML TextField newLastName;
	private Passageiro pEdit;
	
	public void editar(){
		String novoPrimeiroNome, novoUltimoNome;
		
		novoPrimeiroNome = newFirstName.getText();
		novoUltimoNome = newLastName.getText();
		
		Passageiro edita = new Passageiro(novoPrimeiroNome, novoUltimoNome, this.pEdit.getCpf(), this.pEdit.getPassaporte());
		
		try{
			Stage stage = (Stage) btnSalvar.getScene().getWindow(); 
			Fachada.getInstance().editarPassageiro(edita);
			Fachada.getInstance().salvarArquivoPassageiros();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Passageiro atualizado!");
			alert.setHeaderText(null);
			alert.setContentText(edita.toString());
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
	
	public void setPassengerEdit(Passageiro p){
		this.pEdit = p;
	}
	
}	
