package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Faculty;
import logic.Student;

public class StudentFormController implements Initializable {
	private Student s;
		
	@FXML
	private Label lblCLID;
	@FXML
	private Label lblName;
	@FXML
	private Label lblPhone;
	@FXML
	private Label lblEmail;
	@FXML
	private Label lblSub;
	
	@FXML
	private TextField txtCLID;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtPhone;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtSub;
	
	@FXML
	private Button btnclose;
	
	@FXML
	private Button btnSave;
	
	
	private ChatClient client;
	
	ObservableList<String> list;
		
	public void loadStudent(Student s1) {
		this.s=s1;
		this.txtCLID.setText(String.valueOf(s.getId()));
		this.txtName.setText(s.getName());
		this.txtPhone.setText(s.getNumber());		
		this.txtEmail.setText(s.getEmail());
		this.txtSub.setText(String.valueOf(s.getHistory()));
		System.out.printf("%s %s %s %s %s",String.valueOf(s.getId()), s.getName(), s.getNumber(), s.getEmail(), String.valueOf(s.getHistory()));
	}
	
	// creating list of Faculties
//	private void setFacultyComboBox() {
//		ArrayList<String> al = new ArrayList<String>();	
//		al.add("ME");
//		al.add("IE");
//		al.add("SE");
//
//		list = FXCollections.observableArrayList(al);
//		cmbFaculty.setItems(list);
//	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
				
	}
	
	@FXML
	private void closeWindow(ActionEvent event) throws Exception {
	    // Close the current stage
	    Stage stage = (Stage) btnclose.getScene().getWindow();
	    stage.close();
	    
	    Stage previousStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AcademicFrame.fxml"));
		Parent root = loader.load();
				
	    Scene scene = new Scene(root);
	    scene.getStylesheets().add(getClass().getResource("/gui/AcademicFrame.css").toExternalForm());
	    
	    previousStage.setScene(scene);
	    previousStage.show();
	}
	
	@FXML
	private void saveStudentData(ActionEvent event) {
	    try {
	        // Retrieve updated student details from form
	        String ID = txtCLID.getText();
	        String Name = txtName.getText();
	        String Phone = txtPhone.getText();
	        String Email = txtEmail.getText();
	        String History = txtSub.getText();

	        // Format the message to send to the server
	        String message = String.format("SAVE_STUDENT;%s;%s;%s;%s;%s", ID, Name, History, Phone, Email);

	        // Send the updated data to the server
	        ClientUI.chat.accept(message);
	        System.out.println("Updated student data sent to the server: " + ID);
	    } catch (Exception e) {
	        System.out.println("Error: Could not send student data to the server.");
	        e.printStackTrace();
	    }
		
	}
}

		

	

