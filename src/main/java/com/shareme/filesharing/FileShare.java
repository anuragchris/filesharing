package com.shareme.filesharing;

import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@SpringBootApplication
@ComponentScan(basePackages = { "com.sharme.fileSharing.*", "com.shareme.filesharing.service" })
public class FileShare extends Application {

    public static void main(String[] args) {
	// SpringApplication.run(FileShare.class, args);
	Application.launch();
    }

    @Override
    public void init() {
	SpringApplication.run(getClass()).getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage primaryStage) {
	try {
	    URL u = getClass().getResource("/mainview.fxml");
	    Parent root = FXMLLoader.load(getClass().getResource("/mainview.fxml"));
	    // BorderPane root = new BorderPane();
	    Scene scene = new Scene(root);
	    primaryStage.setTitle("Share Me");
	    scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
	    Image img = new Image("mainlogo.png");
	    primaryStage.setScene(scene);
	    primaryStage.getIcons().add(img);
	    primaryStage.show();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
