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
import javafx.stage.Stage;

public class AdicionarVooController {
	private Fachada fachada = Fachada.getInstance();
	
	@FXML Label aviso;
	@FXML Button btnCancelar;
	@FXML Button btnSalvar;
	@FXML TextField horaSaida;
	@FXML TextField minutoSaida;
	@FXML TextField horaChegada;
	@FXML TextField minutoChegada;
	@FXML ChoiceBox<Cidade> cidadeOrigem;
	@FXML ChoiceBox<Cidade> cidadeDestino;
	@FXML ChoiceBox<Aeronave> aeronave;
	@FXML DatePicker dataSaida;
	@FXML DatePicker dataChegada;
	
	public void initializeCities(){
		//Cidades origem
		cidadeOrigem.getItems().add(Cidade.REC);
		cidadeOrigem.getItems().add(Cidade.BSB);
		cidadeOrigem.getItems().add(Cidade.GIG);
		cidadeOrigem.getItems().add(Cidade.GRU);
		//Cidades destino
		cidadeDestino.getItems().add(Cidade.REC);
		cidadeDestino.getItems().add(Cidade.BSB);
		cidadeDestino.getItems().add(Cidade.GIG);
		cidadeDestino.getItems().add(Cidade.GRU);
		cidadeDestino.getItems().add(Cidade.AMS);
		cidadeDestino.getItems().add(Cidade.ARN);
		cidadeDestino.getItems().add(Cidade.CIA);
		cidadeDestino.getItems().add(Cidade.CPH);
		cidadeDestino.getItems().add(Cidade.DUB);
		cidadeDestino.getItems().add(Cidade.HND);
		cidadeDestino.getItems().add(Cidade.ICN);
		cidadeDestino.getItems().add(Cidade.LAS);
		cidadeDestino.getItems().add(Cidade.LGA);
		cidadeDestino.getItems().add(Cidade.LHR);
		cidadeDestino.getItems().add(Cidade.LIS);
		cidadeDestino.getItems().add(Cidade.MAD);
		cidadeDestino.getItems().add(Cidade.ORY);
		cidadeDestino.getItems().add(Cidade.OSL);
		cidadeDestino.getItems().add(Cidade.PEK);
		cidadeDestino.getItems().add(Cidade.TXL);
		cidadeDestino.getItems().add(Cidade.YVR);
		cidadeDestino.getItems().add(Cidade.YYZ);
	}
	
	public void initializeAirPlanes(){
		//Aeronaves
		aeronave.getItems().add(Aeronave.AIRBUS_A320);
		aeronave.getItems().add(Aeronave.AIRBUS_A330);
		aeronave.getItems().add(Aeronave.AIRBUS_A350);
		aeronave.getItems().add(Aeronave.BOEING_737);
		aeronave.getItems().add(Aeronave.BOEING_757);
		aeronave.getItems().add(Aeronave.BOEING_787);
	}
	
	public void initialize(){
		this.initializeCities();
		this.initializeAirPlanes();
	}
	
	
	public void salvar(){
		String leavingHour, leavingMinute, arrivalHour, arrivalMinute;
		
		leavingHour = horaSaida.getText();
		leavingMinute = minutoSaida.getText();
		arrivalHour = horaChegada.getText();
		arrivalMinute = minutoChegada.getText();
		
		if(!leavingHour.equals("") && !leavingMinute.equals("") && dataSaida.getValue() != null && dataChegada.getValue() != null
				&& !arrivalHour.equals("") && !arrivalMinute.equals("") && cidadeOrigem.getValue() != null && 
				cidadeDestino.getValue() != null && aeronave.getValue() != null){
			
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
			
			Voo voo = new Voo(idAleatorio, cidadeOrigem.getValue(), cidadeDestino.getValue(), horaDaSaida, chegadaComFusoHorario, aeronave.getValue());
			
			try{
				Stage stage = (Stage) btnSalvar.getScene().getWindow();
				fachada.adicionarVoo(voo);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Vôo adicionado!");
				alert.setHeaderText(null);
				alert.setContentText(voo.toString());
				alert.showAndWait();
				stage.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else{
			//aviso.setText("Preencha todos os campos!");
		}
		
	}
	
	public void voltar(){
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}
	
}
