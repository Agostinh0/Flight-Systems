package br.ufrpe.flight_systems.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import br.ufrpe.flight_systems.negocio.Fachada;
import br.ufrpe.flight_systems.negocio.beans.Cidade;
import br.ufrpe.flight_systems.negocio.beans.Voo;
import br.ufrpe.flight_systems.negocio.beans.Aeronave;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdicionarVooController {
	private Fachada fachada = Fachada.getInstance();
	
	@FXML Label aviso;
	@FXML Button btnCancelar, btnSalvar;
	@FXML TextField horaSaida, minutoSaida, horaChegada, minutoChegada;
	@FXML ChoiceBox<Cidade> cidadeOrigem, cidadeDestino;
	@FXML ChoiceBox<Aeronave>Aeronave;
	@FXML DatePicker dataSaida, dataChegada;
	
	public void salvar(){
		String leavingHour, leavingMinute, arrivalHour, arrivalMinute;
		
		leavingHour = horaSaida.getText();
		leavingMinute = minutoSaida.getText();
		arrivalHour = horaChegada.getText();
		arrivalMinute = minutoChegada.getText();
		
		if(!leavingHour.equals("") && !leavingMinute.equals("") && dataSaida.getValue() != null && dataChegada.getValue() != null
				&& !arrivalHour.equals("") && !arrivalMinute.equals("") && cidadeOrigem.getValue() != null && 
				cidadeDestino.getValue()!= null){
			
			int hSaida = Integer.parseInt(leavingHour);
			int hChegada = Integer.parseInt(arrivalHour);
			int mSaida = Integer.parseInt(leavingMinute);
			int mChegada = Integer.parseInt(arrivalMinute);
			
			LocalTime horaDecolagem = LocalTime.of(hSaida, mSaida);
			LocalTime horaAterrissagem = LocalTime.of(hChegada, mChegada); 
			
			LocalDate dataDaSaida = LocalDate.of(dataSaida.getValue().getYear(), dataSaida.getValue().getMonth(), dataSaida.getValue().getDayOfMonth());
			LocalDate dataEstimadadaDaChegada = LocalDate.of(dataChegada.getValue().getYear(), dataChegada.getValue().getMonth(), dataChegada.getValue().getDayOfMonth());
			
			LocalDateTime horaDaSaida = LocalDateTime.of(dataDaSaida, horaDecolagem);
			LocalDateTime horaEstimadaDaChegada = LocalDateTime.of(dataEstimadadaDaChegada, horaAterrissagem);
			
			ZonedDateTime chegadaComFusoHorario = 
					ZonedDateTime.of(horaEstimadaDaChegada, cidadeDestino.getValue().getFusoHorario(cidadeDestino.getValue()));
			
			long numero = (long) (Math.random() * 100000);
			long idAleatorio = Math.round(numero);
			
			Voo voo = new Voo(idAleatorio, cidadeOrigem.getValue(), cidadeDestino.getValue(), horaDaSaida, chegadaComFusoHorario, Aeronave.getValue());
			
			try{
				fachada.adicionarVoo(voo);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Vôo adicionado!");
				alert.setHeaderText(null);
				alert.setContentText(voo.toString());
				alert.showAndWait();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else{
			aviso.setText("Preencha todos os campos!");
		}
		
		
		
	}
	
}
