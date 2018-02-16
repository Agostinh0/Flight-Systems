package br.ufrpe.flight_systems.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VooJaRealizadoException extends Exception{

	private static final long serialVersionUID = 4252082106794488582L;	
	
	public VooJaRealizadoException(){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Opera��o n�o permitida.");
		alert.setHeaderText(null);
		alert.setContentText("Esse avi�o j� decolou e pousou");
		alert.showAndWait();
	}
	
}
