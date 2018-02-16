package br.ufrpe.flight_systems.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PossuiBilheteException extends Exception{

	private static final long serialVersionUID = -4988212482976523677L;
	
	public PossuiBilheteException(){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Operação não permitida.");
		alert.setHeaderText(null);
		alert.setContentText("Este passageiro emitiu um ou mais bilhetes.");
		alert.showAndWait();
	}
}
