/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.DataProvider;
import Model.Query;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class CancelAppointmentController implements Initializable {

    @FXML
    private TableView<Appointment> appointmentTbl;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> contactCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, String> startCol;
    @FXML
    private TableColumn<Appointment, String> endCol;
    @FXML
    private Button backBtn;
    @FXML
    private Button deleteBtn;
    
    Stage stage;
    Parent scene;
    
    private void populateAppointmentTbl(){
        appointmentTbl.setItems(DataProvider.getAllAppointments());
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateAppointmentTbl();
    }    


    @FXML
    private void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Controller/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionDelete(ActionEvent event) {
        try {
            int appointmentId = 0;
            Appointment appointment = null;
            if(!DataProvider.getAllAppointments().isEmpty()){
                appointment = appointmentTbl.getSelectionModel().getSelectedItem();
                appointmentId = appointment.getCustomerId();
            }
            String sqlStatement = "DELETE FROM appointment WHERE appointmentId = "
                    + "'" + appointmentId + "';";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel the selected appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Query.makeQuery(sqlStatement);
                DataProvider.deleteAppointment(appointment);
                DataProvider.updateLocalCache();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nothing selected");
            alert.setContentText("Please select an appointment from the table.");
            alert.showAndWait();
        }
    }
    
}
