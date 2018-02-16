package br.ufrpe.flight_systems.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExisteBilheteException extends Exception{

	private static final long serialVersionUID = 3828343099605600777L;
	
	public ExisteBilheteException(){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Opera��o n�o permitida");
		alert.setHeaderText(null);
		alert.setContentText("Existem bilhetes emitidos para este v�o.");
		alert.showAndWait();
	}
}
