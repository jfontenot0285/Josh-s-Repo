/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.BusinessHoursException;
import Model.DataProvider;
import Model.OverlapException;
import Model.PastDateException;
import Model.Query;
import Model.SameTimeException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static javafx.util.Duration.hours;
import static javafx.util.Duration.minutes;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class UpdateAppointmentController implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private ComboBox<String> startHoursBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> endHoursBox;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField locationTxt;
    @FXML
    private TextField contactTxt;
    @FXML
    private ComboBox<String> typeBox;
    @FXML
    private ComboBox<String> startMinuteBox;
    @FXML
    private ComboBox<String> endMinuteBox;
    @FXML
    private TextField titleTxt;
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
    
    Stage stage;
    Parent scene;
    
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    ObservableList<String> typeList = FXCollections.observableArrayList();
    
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
    
    private void populateComboBoxes(){
        hours.addAll("08", "09", "10", "11", "12", "13", "14", "15", "16", "17");
        minutes.addAll("00", "15", "30", "45");
        typeList.addAll("General", "Performance Review", "Conflict Resolution", "Team Building", "Scheduling", "PTO");
       
        startHoursBox.setItems(hours);
        startHoursBox.getSelectionModel().select(0);
        startMinuteBox.setItems(minutes);
        startMinuteBox.getSelectionModel().select(0);
        endHoursBox.setItems(hours);
        endHoursBox.getSelectionModel().select(0);
        endMinuteBox.setItems(minutes);
        endMinuteBox.getSelectionModel().select(0);
        typeBox.setItems(typeList);
        typeBox.getSelectionModel().select(0);
        
        datePicker.setValue(LocalDate.now());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateAppointmentTbl();
        populateComboBoxes();
    }    

    @FXML
    private void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Controller/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionUpdate(ActionEvent event) {
        try{
            Appointment appointment = appointmentTbl.getSelectionModel().getSelectedItem();
            int appointmentId = appointment.getCustomerId();
            String title = titleTxt.getText();
            String description = descriptionTxt.getText();
            String location = locationTxt.getText();
            String contact = contactTxt.getText();
            String type = typeBox.getSelectionModel().getSelectedItem();
            LocalDate date = datePicker.getValue();
            String startHour = startHoursBox.getValue();
            String endHour = endHoursBox.getValue();
            String startMinute = startMinuteBox.getValue();
            String endMinute = endMinuteBox.getValue();
         
            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            //get the LocalDateTime
            LocalDateTime startLdt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(startHour), Integer.parseInt(startMinute));
            LocalDateTime endLdt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMinute));
            
            for(Appointment a : DataProvider.getAllAppointments()){
                LocalDateTime scheduledStartParse = LocalDateTime.parse(a.getStartTime(), customFormatter);
                LocalDateTime scheduledEndParse = LocalDateTime.parse(a.getEndTime(), customFormatter);
                LocalDateTime scheduledStart = LocalDateTime.of(scheduledStartParse.getYear(), scheduledStartParse.getMonthValue(), scheduledStartParse.getDayOfMonth(), scheduledStartParse.getHour(), scheduledStartParse.getMinute());
                LocalDateTime scheduledEnd = LocalDateTime.of(scheduledEndParse.getYear(), scheduledEndParse.getMonthValue(), scheduledEndParse.getDayOfMonth(), scheduledEndParse.getHour(), scheduledEndParse.getMinute());
                
                if(startLdt.isBefore(scheduledEnd) && endLdt.isAfter(scheduledStart))
                    throw new OverlapException("Scheduled appointment conflicts with another appointment.");
            }
            
            if(startLdt.isAfter(LocalDateTime.of(startLdt.getYear(), startLdt.getMonth(), startLdt.getDayOfMonth(), Integer.parseInt("16"), Integer.parseInt("45"))))
                throw new BusinessHoursException("Selected time is outside of business hours.");
            
            if(endLdt.isAfter(LocalDateTime.of(startLdt.getYear(), startLdt.getMonth(), startLdt.getDayOfMonth(), Integer.parseInt("17"), Integer.parseInt("00"))))
                throw new BusinessHoursException("Selected time is outside of business hours.");
            
            if(startLdt.equals(endLdt))
                throw new SameTimeException("Start time and end time cannot be the same.");
            
            if(startLdt.isBefore(LocalDateTime.now()) || endLdt.isBefore(LocalDateTime.now()))
                throw new PastDateException("Scheduled time cannot be in the past.");
            
            if(startLdt.isAfter(endLdt) || endLdt.isBefore(startLdt))
                throw new PastDateException("Start time must come before end time.");
            
            ZonedDateTime startLocZdt = ZonedDateTime.of(startLdt, ZoneId.systemDefault());
            ZonedDateTime endLocZdt = ZonedDateTime.of(endLdt, ZoneId.systemDefault());     
            ZonedDateTime startUtcZdt = startLocZdt.withZoneSameInstant(ZoneOffset.UTC);
            ZonedDateTime endUtcZdt = endLocZdt.withZoneSameInstant(ZoneOffset.UTC);
            
            String start = customFormatter.format(startUtcZdt);
            String end = customFormatter.format(endUtcZdt);
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm appointment changes?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){    
                if(!title.isEmpty()){
                    System.out.println("TITLE IS NOT EMPTY");
                    String sqlStatement = "UPDATE appointment "
                        + "SET title = " + "'" + title + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER()"
                        + "WHERE appointmentId = " + appointmentId + ";";
                    Query.makeQuery(sqlStatement);
                }
                
                if(!description.isEmpty()){
                    String sqlStatement = "UPDATE appointment "
                        + "SET description = " + "'" + description + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER()"
                        + "WHERE appointmentId = " + appointmentId + ";";
                    Query.makeQuery(sqlStatement);
                    
                }
                
                if(!location.isEmpty()){
                    String sqlStatement = "UPDATE appointment "
                        + "SET location = " + "'" + location + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER()"
                        + "WHERE appointmentId = " + appointmentId + ";";
                    Query.makeQuery(sqlStatement);
                    
                }
                
                if(!contact.isEmpty()){
                    String sqlStatement = "UPDATE appointment "
                        + "SET contact = " + "'" + contact + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER()"
                        + "WHERE appointmentId = " + appointmentId + ";";
                    Query.makeQuery(sqlStatement);
                    
                }
                
                String sqlStatement = "UPDATE appointment "
                        + "SET type = " + "'" + type + "', "
                        + "start = " + "'" + start + "', "
                        + "end = " + "'" + end + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER()"
                        + "WHERE appointmentId = " + appointmentId + ";";
                    Query.makeQuery(sqlStatement);
                DataProvider.updateLocalCache();
            }
            
                 
        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nothing selected");
            alert.setContentText("Please select a customer from the table.");
            alert.showAndWait();
        }catch (OverlapException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Overlapping Appointments");
            alert.setContentText("Scheduled appointment conflicts with another appointment.");
            alert.showAndWait();
        }catch (BusinessHoursException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Outside Business Hours");
            alert.setContentText("Selected time is outside of business hours.");
            alert.showAndWait();
        }catch (SameTimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Outside Business Hours");
            alert.setContentText("Start time and end time cannot be the same.");
            alert.showAndWait();
        }catch (PastDateException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Past Date");
            alert.setContentText(e +"");
            alert.showAndWait();
        }
    }

    
}
