/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.BusinessHoursException;
import Model.Customer;
import Model.DataProvider;
import Model.InputException;
import Model.OverlapException;
import Model.PastDateException;
import Model.Query;
import Model.SameTimeException;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
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

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class ScheduleAppointmentController implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private TableView<Customer> customerTbl;
    @FXML
    private TableColumn<Customer, Integer> idCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> addCol;
    @FXML
    private TableColumn<Customer, String> add2Col;
    @FXML
    private TableColumn<Customer, String> zipCol;
    @FXML
    private TableColumn<Customer, String> cityCol;
    @FXML
    private TableColumn<Customer, String> countryCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;
    @FXML
    private Button scheduleBtn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField titleTxt;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField locationTxt;
    @FXML
    private TextField contactTxt;
    @FXML
    private ComboBox<String> typeBox;

    Stage stage;
    Parent scene;
    
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    ObservableList<String> typeList = FXCollections.observableArrayList();
    
    @FXML
    private ComboBox<String> startHoursBox;
    @FXML
    private ComboBox<String> endHoursBox;
    @FXML
    private ComboBox<String> startMinuteBox;
    @FXML
    private ComboBox<String> endMinuteBox;
    
    private void validateInput(String title, String description, String location, 
            String contact) throws InputException{
       
        if(title.isEmpty())
            throw new InputException("Title field cannot be empty.");
        if(description.isEmpty())
            throw new InputException("Description field cannot be empty.");
        if(location.isEmpty())
            throw new InputException("Location field cannot be empty.");
        if(contact.isEmpty())
            throw new InputException("Contact field cannot be empty.");     
    }
    
    private void populateCustomerTbl(){
        customerTbl.setItems(DataProvider.getAllCustomers());
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        add2Col.setCellValueFactory(new PropertyValueFactory<>("address2"));
        zipCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateCustomerTbl();
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
    private void onActionSchedule(ActionEvent event) {
        try {
            Customer customer = customerTbl.getSelectionModel().getSelectedItem();
            int customerId = customer.getCustomerId();
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
            
            validateInput(title, description, location, contact);
            
            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            
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
            
            String sqlStatement = "INSERT INTO appointment (appointmentId, customerId, userId, "
                    + "title, description, location, contact, type, url, start, end, createDate, "
                    + "createdBy, lastUpdate, lastUpdateBy) "
                    + "VALUES(NULL, " + customerId + ", " + 1 + ", "
                    + "'" + title + "', " + "'" + description + "', " + "'" + location
                    + "', " + "'" + contact + "', " + "'" + type + "', url, " + "'" + start
                    + "', " + "'" + end + "', CURRENT_TIMESTAMP(), CURRENT_USER(), "
                    + "CURRENT_TIMESTAMP(), CURRENT_USER());";
            
            Query.makeQuery(sqlStatement);
            DataProvider.updateLocalCache();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment successfully scheduled.");
            Optional<ButtonType> result = alert.showAndWait();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nothing selected");
            alert.setContentText("Please select a customer from the table.");
            alert.showAndWait();
        } catch (OverlapException e){
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
        }catch (InputException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty field");
            alert.setContentText(e +"");
            alert.showAndWait();
        }catch (PastDateException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Past Date");
            alert.setContentText(e +"");
            alert.showAndWait();
        }
        
    }
    
}
