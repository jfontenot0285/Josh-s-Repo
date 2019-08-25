/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import Model.DataProvider;
import Model.InputException;
import Model.Query;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class AddRecordController implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private Button addBtn;
    @FXML
    private TextField customerAddressTxt;
    @FXML
    private TextField customerNameTxt;
    @FXML
    private TextField customerPhoneTxt;
    @FXML
    private TextField customerAddress2Txt;
    @FXML
    private TextField postalCodeTxt;
    @FXML
    private ComboBox<String> cityComboBox;
    @FXML
    private ComboBox<String> countryComboBox;
    
    Stage stage;
    Parent scene;
    
    ObservableList<String> cityList = FXCollections.observableArrayList();
    ObservableList<String> countryList = FXCollections.observableArrayList();
    
    private void populateComboBoxes(){
        cityList.add("Phoenix");
        cityList.add("New York City");
        cityList.add("London");
        countryList.add("United States");
        countryList.add("England");
        
        cityComboBox.setItems(cityList);
        cityComboBox.getSelectionModel().select(0);
        countryComboBox.setItems(countryList);
        countryComboBox.getSelectionModel().select(0);
    }
    
    private void validateInput(String name, String address, String address2, 
            String postalCode, String phone) throws InputException{
        if(name.isEmpty())
            throw new IllegalArgumentException("Name field");
        if(name.length() > 45)
            throw new InputException("Name length cannot exceed 45 characters.");
            
        if(address.isEmpty())
            throw new IllegalArgumentException("Address field");
        if(address.length() > 50)
            throw new InputException("Address length cannot exceed 50 characters.");
        if(address2.length() > 50)
            throw new InputException("Address 2 length cannot exceed 50 characters.");
        if(address2.isEmpty())
            throw new IllegalArgumentException("Address 2 field");
            
        if(postalCode.length() > 10)
            throw new InputException("Postal code length cannot exceed 10 characters.");
        if(postalCode.isEmpty())
            throw new IllegalArgumentException("Postal code field");
            
        if(phone.isEmpty())
            throw new IllegalArgumentException("Phone field");
        if(phone.length() > 20)
            throw new InputException("Phone length cannot exceed 20 characters.");
    }
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void onActionAdd(ActionEvent event) {
        try{
            String name = customerNameTxt.getText();
            String address = customerAddressTxt.getText();
            String address2 = customerAddress2Txt.getText();
            String postalCode = postalCodeTxt.getText();
            String phone = customerPhoneTxt.getText();
            String city = cityComboBox.getSelectionModel().getSelectedItem();
            String country = countryComboBox.getSelectionModel().getSelectedItem();
            
            validateInput(name, address, address2, postalCode, phone);
            
            String countrySqlStatement = "INSERT INTO country (countryId, country, createDate, lastUpdate, createdBy, lastUpdateBy) " +
                "VALUES(NULL, " + "'" + country + "', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_USER(), CURRENT_USER());";
            String citySqlStatement = "INSERT INTO city (cityId, city, countryId, createDate, lastUpdate, createdBy, lastUpdateBy) " +
                "VALUES(NULL, " + "'" + city + "', LAST_INSERT_ID(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_USER(), CURRENT_USER());";
            String addressSqlStatement = "INSERT INTO address (addressId, address, address2, cityId, postalCode, phone, createDate, lastUpdate, createdBy, lastUpdateBy) " +
                "VALUES(NULL, " + "'" + address + "', " + "'" + address2 + 
                    "', LAST_INSERT_ID(), " + 
                    "'" + postalCode + "', " + "'" + phone + "', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_USER(), CURRENT_USER());";
            String customerSqlStatement = "INSERT INTO customer (customerId, customerName, addressId, active, createDate, lastUpdate, createdBy, lastUpdateBy) " +
                "VALUES(NULL, " + "'" + name + "', LAST_INSERT_ID(), 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_USER(), CURRENT_USER());";
                
            Query.makeQuery(countrySqlStatement);
            Query.makeQuery(citySqlStatement);
            Query.makeQuery(addressSqlStatement);
            Query.makeQuery(customerSqlStatement);
            
            DataProvider.updateLocalCache();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer successfully added to database.");
            Optional<ButtonType> result = alert.showAndWait();
            
        }catch(IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing data");
            alert.setContentText(e + " must not be empty.");
            alert.showAndWait();
        }catch(InputException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error.");
            alert.setContentText(e + "");
            alert.showAndWait();
        }
    }
}
