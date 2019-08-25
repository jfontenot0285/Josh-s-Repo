/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.DataProvider;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class ViewCalendarController implements Initializable {

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
    
    Stage stage;
    Parent scene;

    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    ObservableList<String> typeList = FXCollections.observableArrayList();
    @FXML
    private RadioButton weekRdo;
    @FXML
    private ToggleGroup groupA;
    @FXML
    private RadioButton monthRdo;
    @FXML
    private Button refreshBtn;
    
    
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
    private void onActionWeek(ActionEvent event) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus7 = now.plusDays(7);
        FilteredList<Appointment> filteredData = new FilteredList<>(DataProvider.getAllAppointments());
        filteredData.setPredicate(row -> { //lambda expression for filter makes code more readable and concise
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime rowDate = LocalDateTime.parse(row.getStartTime(), formatter);
            return rowDate.isAfter(now) && rowDate.isBefore(nowPlus7);
        });
        appointmentTbl.setItems(filteredData);
    }

    @FXML
    private void onActionMonth(ActionEvent event) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.with(lastDayOfMonth());
        FilteredList<Appointment> filteredData = new FilteredList<>(DataProvider.getAllAppointments());
        filteredData.setPredicate(row -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime rowDate = LocalDateTime.parse(row.getStartTime(), formatter);
            return rowDate.isAfter(now) && rowDate.isBefore(end);
        });
        appointmentTbl.setItems(filteredData);
    }  

    @FXML
    private void onActionRefresh(ActionEvent event) {
        populateAppointmentTbl();
    }
}
