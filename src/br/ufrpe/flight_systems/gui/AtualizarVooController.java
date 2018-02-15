package br.ufrpe.flight_systems.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import br.ufrpe.flight_systems.negocio.Fachada;
import br.ufrpe.flight_systems.negocio.beans.Aeronave;
import br.ufrpe.flight_systems.negocio.beans.Cidade;
import br.ufrpe.flight_systems.negocio.beans.Voo;
public class AtualizarVooController {
	
	@FXML Label aviso;
	@FXML Button btnCancelar;


	@FXML Button btnSalvar;
	@FXML TextField novaHoraSaida;
	@FXML TextField novaHoraChegada;
	@FXML TextField novoMinutoSaida;
	@FXML TextField novoMinutoChegada;
	@FXML ChoiceBox<Cidade> novaCidadeOrigem;
	@FXML ChoiceBox<Cidade> novaCidadeDestino;
	@FXML ChoiceBox<Aeronave> novaAeronave;
	@FXML DatePicker novaDataSaida;
	@FXML DatePicker novaDataChegada;
	private Voo vEdit;
	
	public void initializeCities(){
		//Cidades origem
		novaCidadeOrigem.getItems().add(Cidade.REC);
		novaCidadeOrigem.getItems().add(Cidade.BSB);
		novaCidadeOrigem.getItems().add(Cidade.GIG);
		novaCidadeOrigem.getItems().add(Cidade.GRU);
		//Cidades destino
		novaCidadeDestino.getItems().add(Cidade.REC);
		novaCidadeDestino.getItems().add(Cidade.BSB);
		novaCidadeDestino.getItems().add(Cidade.GIG);
		novaCidadeDestino.getItems().add(Cidade.GRU);
		novaCidadeDestino.getItems().add(Cidade.AMS);
		novaCidadeDestino.getItems().add(Cidade.ARN);
		novaCidadeDestino.getItems().add(Cidade.CIA);
		novaCidadeDestino.getItems().add(Cidade.CPH);
		novaCidadeDestino.getItems().add(Cidade.DUB);
		novaCidadeDestino.getItems().add(Cidade.HND);
		novaCidadeDestino.getItems().add(Cidade.ICN);
		novaCidadeDestino.getItems().add(Cidade.LAS);
		novaCidadeDestino.getItems().add(Cidade.LGA);
		novaCidadeDestino.getItems().add(Cidade.LHR);
		novaCidadeDestino.getItems().add(Cidade.LIS);
		novaCidadeDestino.getItems().add(Cidade.MAD);
		novaCidadeDestino.getItems().add(Cidade.ORY);
		novaCidadeDestino.getItems().add(Cidade.OSL);
		novaCidadeDestino.getItems().add(Cidade.PEK);
		novaCidadeDestino.getItems().add(Cidade.TXL);
		novaCidadeDestino.getItems().add(Cidade.YVR);
		novaCidadeDestino.getItems().add(Cidade.YYZ);
	}
	
	public void initializeAirPlanes(){
		//Aeronaves
		novaAeronave.getItems().add(Aeronave.AIRBUS_A320);
		novaAeronave.getItems().add(Aeronave.AIRBUS_A330);
		novaAeronave.getItems().add(Aeronave.AIRBUS_A350);
		novaAeronave.getItems().add(Aeronave.BOEING_737);
		novaAeronave.getItems().add(Aeronave.BOEING_757);
		novaAeronave.getItems().add(Aeronave.BOEING_787);
	}
	
	public void initialize(){
		this.initializeCities();
		this.initializeAirPlanes();
	}
	
	public void salvar(){
		String leavingHour, leavingMinute, arrivalHour, arrivalMinute;
		
		leavingHour = novaHoraSaida.getText();
		leavingMinute = novoMinutoSaida.getText();
		arrivalHour = novaHoraChegada.getText();
		arrivalMinute = novoMinutoChegada.getText();
		
		if(!leavingHour.equals("") && !leavingMinute.equals("") && novaDataSaida.getValue() != null && novaDataChegada.getValue() != null
				&& !arrivalHour.equals("") && !arrivalMinute.equals("") && novaCidadeOrigem.getValue() != null && 
				novaCidadeDestino.getValue() != null && novaAeronave.getValue() != null){
			
			int hSaida = Integer.parseInt(leavingHour);
			int hChegada = Integer.parseInt(arrivalHour);
			int mSaida = Integer.parseInt(leavingMinute);
			int mChegada = Integer.parseInt(arrivalMinute);
			
			LocalTime horaDecolagem = LocalTime.of(hSaida, mSaida);
			LocalTime horaAterrissagem = LocalTime.of(hChegada, mChegada); 
			
			LocalDate dataDaSaida = LocalDate.of(novaDataSaida.getValue().getYear(), novaDataSaida.getValue().getMonth(), novaDataSaida.getValue().getDayOfMonth());
			LocalDate dataEstimadadaDaChegada = LocalDate.of(novaDataChegada.getValue().getYear(), novaDataChegada.getValue().getMonth(), novaDataChegada.getValue().getDayOfMonth());
			
			LocalDateTime horaDaSaida = LocalDateTime.of(dataDaSaida, horaDecolagem);
			LocalDateTime horaEstimadaDaChegada = LocalDateTime.of(dataEstimadadaDaChegada, horaAterrissagem);
			
			ZonedDateTime saidaComFusoHorario =
					ZonedDateTime.of(horaDaSaida, novaCidadeOrigem.getValue().getFusoHorario());
			
			ZonedDateTime chegadaComFusoHorario = 
					ZonedDateTime.of(horaEstimadaDaChegada, novaCidadeDestino.getValue().getFusoHorario());
			
			try{	
				if(vEdit.getHoraSaida().isAfter(ZonedDateTime.now())){
					Voo edita = new Voo(vEdit.getId(), novaCidadeOrigem.getValue(), novaCidadeDestino.getValue(), saidaComFusoHorario, 
						chegadaComFusoHorario, novaAeronave.getValue());
					Stage stage = (Stage) btnSalvar.getScene().getWindow();
					Fachada.getInstance().editarVoo(edita);
					Fachada.getInstance().salvarArquivoVoos();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Vôo atualizado!");
					alert.setHeaderText(null);
					alert.setContentText(edita.toString());
					alert.showAndWait();
						stage.close();
						
				}else if(vEdit.getHoraSaida().isBefore(ZonedDateTime.now()) && 
							vEdit.getHoraEstimadaChegada().isAfter(ZonedDateTime.now())){
					
					Voo edita = new Voo(vEdit.getId(), novaCidadeOrigem.getValue(), novaCidadeDestino.getValue(), vEdit.getHoraSaida() , 
						chegadaComFusoHorario, novaAeronave.getValue());
					Stage stage = (Stage) btnSalvar.getScene().getWindow();
					Fachada.getInstance().editarVoo(edita);
					Fachada.getInstance().salvarArquivoVoos();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Vôo atualizado!");
					alert.setHeaderText(null);
					alert.setContentText(edita.toString());
					alert.showAndWait();
					stage.close();
			}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
		public void voltar(){
			Stage stage = (Stage) btnCancelar.getScene().getWindow();
			stage.close();
		}
	
		public void setFlightEdit(Voo v){
			this.vEdit = v;
		}
	}

