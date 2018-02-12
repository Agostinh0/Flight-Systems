package br.ufrpe.flight_systems.gui;

import br.ufrpe.flight_systems.negocio.Fachada;
import br.ufrpe.flight_systems.negocio.beans.Passageiro;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AtualizarPassageiroController {
	
	@FXML Label aviso;
	@FXML Button btnCancelar, btnSalvar;
	@FXML TextField newFirstName, newLastName, newCpf, newPassport;
	
	public void editar(){
		String novoPrimeiroNome, novoUltimoNome, novoCpf, novoPassaporte;
		
		novoPrimeiroNome = newFirstName.getText();
		novoUltimoNome = newLastName.getText();
		novoCpf = newCpf.getText();
		novoPassaporte = newPassport.getText();
		
		Passageiro edita = new Passageiro(novoPrimeiroNome, novoUltimoNome, novoCpf, novoPassaporte);
		
		try{
			Fachada.getInstance().editarPassageiro(edita);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Passageiro editado!");
			alert.setHeaderText(null);
			alert.setContentText(edita.toString());
			alert.showAndWait();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void voltar(){
		
	}
	
}	
