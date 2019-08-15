/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.DBConnection;
import Model.DataProvider;
import Model.Query;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class LoginFormController implements Initializable {

    @FXML
    private TextField userNameTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Button loginBtn;
    @FXML
    private Button exitBtn;

    final String filename = "src/logs/logins.txt";
    Stage stage;
    Parent scene;
    @FXML
    private Label loginLbl;
    @FXML
    private Label userNameLbl;
    @FXML
    private Label passwordLbl;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(Locale.getDefault());
        rb=ResourceBundle.getBundle("language_files/rb");
        loginLbl.setText(rb.getString("Login"));
        userNameLbl.setText(rb.getString("User"));
        passwordLbl.setText(rb.getString("Password"));
        loginBtn.setText(rb.getString("Login"));
        exitBtn.setText(rb.getString("Exit"));
    }    

    @FXML
    private void onActionLogin(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        
        ResourceBundle rb = ResourceBundle.getBundle("language_files/rb");
	String title = rb.getString("title");
        String message = rb.getString("message");
 
        FileWriter fwriter = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        Calendar myCalendar = Calendar.getInstance();

        try{
            String name = userNameTxt.getText();
            String password = passwordTxt.getText();

            if(!"test".equals(name) || !"test".equals(password)){
                outputFile.append("Failed login attempt by user: " + "'" +
                name + "'" + ", " + myCalendar.getTime() + "\n");
                outputFile.close();
                throw new IllegalArgumentException();
            }
            
            outputFile.append("Successful login attempt by user: " + "'" +
            name + ", " + myCalendar.getTime() + "\n");
            outputFile.close();
                
            Model.DBConnection.openConnection();
            
            DataProvider.updateLocalCache();
            
            /*lambda expressions below replace enhanced for loop for traversing appointments list
              and comparing times. code looks more straightforward and readable.*/
            DataProvider.getAllAppointments().stream().map((appointment) -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startAppointment = LocalDateTime.parse(appointment.getStartTime(), formatter);
                return startAppointment;
            }).map((startAppointment) -> {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime fromTemp = LocalDateTime.from(startAppointment);
                long minutes = fromTemp.until(now, ChronoUnit.MINUTES);
                return minutes;                
            }).map((minutes) -> {
                System.out.println(minutes);
                return minutes;
            }).filter((minutes) -> (minutes >= -15 && minutes < 15)).map((_item) -> new Alert(Alert.AlertType.INFORMATION, "You have an appointment pending.")).forEachOrdered((alert) -> {
                Optional<ButtonType> result = alert.showAndWait();
            });
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Controller/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            
        } catch(IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

    @FXML
    private void onActionExit(ActionEvent event) {
        ResourceBundle rb = ResourceBundle.getBundle("language_files/rb");
        String quit = rb.getString("quit");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, quit);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.exit(0);
        } 
    }
}
