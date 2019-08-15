/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.Customer;
import Model.Query;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class ConsultantSchedulesController implements Initializable {
    ObservableList<Customer> consultantList = FXCollections.observableArrayList();
    ObservableList<Appointment> scheduleList = FXCollections.observableArrayList();
    Stage stage;
    Parent scene;
    
    @FXML
    private TableView<Customer> consultantTbl;
    @FXML
    private Button backBtn;
    @FXML
    private TableColumn<Customer, Integer> idCol;
    @FXML
    private TableColumn<Customer, String> consultantCol;
    @FXML
    private TableView<Appointment> appTbl;
    @FXML
    private TableColumn<Appointment, String> startCol;
    @FXML
    private TableColumn<Appointment, String> endCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        consultantList.clear();
        String sqlStatement = "SELECT customerId, customerName "
                + "from customer; ";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        try {
            while(result.next()){
                int customerId = result.getInt("customerId");
                String customerName = result.getString("customerName");
                
                Customer customer = new Customer(customerId, customerName);
                consultantList.add(customer); 
            }
        } catch (SQLException ex) {
        Logger.getLogger(UpdateRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        consultantTbl.setItems(consultantList);
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        consultantCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }    

    @FXML
    private void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Controller/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionMouseClick(MouseEvent event) throws IOException {
        Customer c = consultantTbl.getSelectionModel().getSelectedItem();
        int customerId = c.getCustomerId();
        
        scheduleList.clear();
        String sqlStatement = "SELECT start, end "
                + "from appointment "
                + "WHERE customerId = " + customerId;
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        try {
            while(result.next()){
                String start = result.getString("start");
                String end = result.getString("end");
                
                Appointment a = new Appointment(start, end);
                scheduleList.add(a); 
            }
        } catch (SQLException ex) {
        Logger.getLogger(UpdateRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        appTbl.setItems(scheduleList);
        startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }
    
}
