package br.ufrpe.flight_systems.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	private static BorderPane root = new BorderPane();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try{
			BorderPane root = FXMLLoader.load(getClass().getResource("/br/ufrpe/flight_systems/gui/FlightSystemsGUI.fxml"));
			Scene scene = new Scene(root, 800, 500);
			primaryStage.setTitle("Flight Systems");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		launch(args);
	}
	
	public static BorderPane getRoot(){
		return root;
	}
}
