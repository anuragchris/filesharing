package com.shareme.filesharing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) {
	try {
	    Parent root = FXMLLoader.load(getClass().getResource("mainview.fxml"));
	    // BorderPane root = new BorderPane();
	    Scene scene = new Scene(root);
	    primaryStage.setTitle("Share Me");
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    Image img = new Image("/application/mainlogo.png");
	    primaryStage.setScene(scene);
	    primaryStage.getIcons().add(img);
	    primaryStage.show();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	launch(args);
    }
}
