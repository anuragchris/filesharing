package com.shareme.filesharing;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.net.InetAddresses;
import com.shareme.fileShare.model.SendFileDetails;
import com.shareme.filesharing.service.FileProcessing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Component
public class controller {

    @FXML
    private Label label;

    private static SendFileDetails fileDetails = new SendFileDetails();
    // private static controller setdetails=new controller();

    @FXML
    public void sendview(ActionEvent event) throws IOException {
	Stage primaryStage1 = new Stage();
	Parent root1 = FXMLLoader.load(getClass().getResource("/sv.fxml"));
	Image img = new Image("/sendlogo.png");
	Scene scene1 = new Scene(root1);
	primaryStage1.getIcons().add(img);
	primaryStage1.setTitle("Send");
	primaryStage1.setScene(scene1);
	primaryStage1.initModality(Modality.APPLICATION_MODAL);
	primaryStage1.show();

    }

    @FXML
    public void recieveview() throws IOException {
	Stage primaryStage2 = new Stage();
	Parent root2 = FXMLLoader.load(getClass().getResource("/receive.fxml"));
	Image img = new Image("/receivelogo.png");
	Scene scene2 = new Scene(root2);
	primaryStage2.getIcons().add(img);
	primaryStage2.setTitle("Received");
	primaryStage2.setScene(scene2);
	primaryStage2.initModality(Modality.APPLICATION_MODAL);
	primaryStage2.show();
    }

    @FXML
    private TextField userport;

    @FXML
    public void setport(ActionEvent et) throws IOException {
	Stage primaryStage3 = new Stage();
	Parent root3 = FXMLLoader.load(getClass().getResource("/pv.fxml"));
	Image img = new Image("/portlogo.png");
	Scene scene3 = new Scene(root3);
	primaryStage3.getIcons().add(img);
	primaryStage3.setTitle("Set Port");
	primaryStage3.setScene(scene3);
	primaryStage3.initModality(Modality.APPLICATION_MODAL);
	primaryStage3.show();
	et.consume();
	// String port = userport.getText();
	// System.out.println(port);

    }

    @FXML
    private Label pvlb;

    @FXML
    private Button set;

    @FXML
    public void setptlb(ActionEvent et) throws IOException {
	String port = userport.getText();
	boolean valid = validIP(port);
	if (valid) {
	    pvlb.setText("Port Set");
	    // SendFileDetails ip = new SendFileDetails();
	    // ip.setRemoteMachineAddress(port);
	    fileDetails.setRemoteMachineAddress(port);
	    // setdetails.setportcheck();
	    System.out.println(port);
	    // set.setCancelButton(true);
	} else {
	    pvlb.setText("Enter a valid IP Address");
	    System.out.println("Enter a valid IP Address");
	}
    }

    static boolean validIP(String port) throws IllegalArgumentException {
	if (Strings.isNullOrEmpty(port)) {
	    return false;
	}
	if (InetAddresses.isInetAddress(port)) {
	    return true;
	} else {
	    return false;
	}
    }

    public String stloc(ActionEvent evt) throws IOException {
	// Stage loc = new Stage();
	// Parent rt =
	// FXMLLoader.load(getClass().getResource("/application/setloc.fxml"));
	// Image img = new Image("/application/portlogo.png");
	// Scene sc = new Scene(rt);
	// loc.getIcons().add(img);
	// loc.setTitle("Set Location");
	// loc.setScene(sc);
	// loc.show();
	Stage primaryStage = new Stage();
	primaryStage.setTitle("Set Default Location");
	DirectoryChooser directoryChooser = new DirectoryChooser();
	// directoryChooser.setInitialDirectory(new
	// File("C:\\Users\\anujg\\Downloads"));
	File selectedDirectory = directoryChooser.showDialog(primaryStage);
	String directory = selectedDirectory.getAbsolutePath();
	System.out.println(directory);
	return directory;
    }

    @FXML
    private Label txt;

    public void loclb(ActionEvent et) throws IOException {
	txt.setText("Location Set");
    }

    @FXML
    private TextField slb;
    @FXML
    private TextField loclb;
    @FXML
    private Button snbr;
    @FXML
    private Button lobr;
    @FXML
    private Label filesSelected;

    @FXML
    public void brs(ActionEvent et) {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Open Multiple Files");
	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
	List<File> file = fileChooser.showOpenMultipleDialog(null);
	fileDetails.setFileDetails(file);
	fileDetails.setFileDetails(file);
	filesSelected.setText("Total Files Selected : " + file.size());
	System.out.println(file);

	// return file;
	// String[] names = new String[file.size()];
	// names = name(file);
	// SendFileDetails files = new SendFileDetails();
	// files.setFileNames(names);
	// for (int i = 0; i < names.length; i++) {
	// System.out.print(names[i] + " ");
	// }
	// return names;
	// long size = Files.size();
	// return files.getFileNames();
    }

    static String[] name(List<File> file) {

	String fileName[] = new String[file.size()];
	File[] f = new File[file.size()];
	for (int i = 0; i < file.size(); i++) {
	    f[i] = file.get(i);
	    fileName[i] = f[i].getName();
	}
	return fileName;

    }

    @FXML
    public void locbr(ActionEvent et) throws IOException {
	DirectoryChooser ch = new DirectoryChooser();
	ch.setTitle("Set Location");
	File f = ch.showDialog(null);
	if (f != null) {
	    loclb.setText(f.getAbsolutePath());
	}
    }

    @FXML
    public void abt(ActionEvent et) throws IOException {
	Stage primaryStage1 = new Stage();
	Parent root1 = FXMLLoader.load(getClass().getResource("/about.fxml"));
	Image img = new Image("/mainlogo.png");
	Scene scene1 = new Scene(root1);
	primaryStage1.getIcons().add(img);
	primaryStage1.setTitle("Share Me");
	primaryStage1.setScene(scene1);
	primaryStage1.initModality(Modality.APPLICATION_MODAL);
	primaryStage1.show();
    }

    @FXML
    public void sendfile(ActionEvent et) {
	FileProcessing fileProcessing = new FileProcessing();
	fileProcessing.bigFile(fileDetails.getFileDetails(), fileDetails.getRemoteMachineAddress());
	et.consume();
    }

    @FXML
    public void exitapplication(ActionEvent et) {
	System.exit(0);
    }

    @FXML
    public boolean alertwindow() throws IOException {
	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	alert.setTitle("Notification");
	alert.setContentText("Do you want to accept the files?");
	alert.setHeaderText(null);
	alert.initModality(Modality.APPLICATION_MODAL);
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK) {
	    controller ob = new controller();
	    ob.recieveview();
	    return true;
	} else
	    return false;

    }

}
