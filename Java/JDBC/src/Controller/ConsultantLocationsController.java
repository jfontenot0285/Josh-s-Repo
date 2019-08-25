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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Josh
 */
public class ConsultantLocationsController implements Initializable {

    @FXML
    private TableView<Customer> consultantTbl;
    @FXML
    private TableColumn<Customer, Integer> idCol;
    @FXML
    private TableColumn<Customer, String> consultantCol;
    @FXML
    private Button backBtn;
    @FXML
    private TableView<Customer> locationTbl;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> address2Col;
    @FXML
    private TableColumn<Customer, String> cityCol;
    @FXML
    private TableColumn<Customer, String> postalCodeCol;
    @FXML
    private TableColumn<Customer, String> countryCol;

    ObservableList<Customer> consultantList = FXCollections.observableArrayList();
    ObservableList<Customer> locationList = FXCollections.observableArrayList();
    Stage stage;
    Parent scene;
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
    private void onActionMouseClick(MouseEvent event) {
        Customer c = consultantTbl.getSelectionModel().getSelectedItem();
        int customerId = c.getCustomerId();
        
        locationList.clear();
        String sqlStatement = "SELECT customer.customerId, address.addressId, address.address, address.address2, address.postalCode, " 
                + "city.cityId, city.city, country.countryId, country.country FROM customer "
                + "LEFT JOIN address ON customer.addressId = address.addressId LEFT JOIN city "
                + "ON address.cityId = city.cityId LEFT JOIN country ON city.countryId = country.countryId "
                + "WHERE customerId = " + customerId + ";";
                
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        try {
            while(result.next()){
                String address = result.getString("address");
                String address2 = result.getString("address2");
                String city = result.getString("city");
                String postalCode = result.getString("postalCode");
                String country = result.getString("country");
                
                Customer cus = new Customer(address, address2, city, postalCode, country);
                locationList.add(cus); 
            }
        } catch (SQLException ex) {
        Logger.getLogger(UpdateRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        locationTbl.setItems(locationList);
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        address2Col.setCellValueFactory(new PropertyValueFactory<>("address2"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
    }

    @FXML
    private void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Controller/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
