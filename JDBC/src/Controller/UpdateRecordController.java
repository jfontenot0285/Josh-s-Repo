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
public class UpdateRecordController implements Initializable {

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
    private Button backBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private ComboBox<String> cityComboBox;
    @FXML
    private ComboBox<String> countryComboBox;
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
    
    Stage stage;
    Parent scene;
    
    ObservableList<String> cityList = FXCollections.observableArrayList();
    ObservableList<String> countryList = FXCollections.observableArrayList();
    
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
       
        if(name.length() > 45)
            throw new InputException("Name length cannot exceed 45 characters.");
        if(address.length() > 50)
            throw new InputException("Address length cannot exceed 50 characters.");
        if(address2.length() > 50)
            throw new InputException("Address 2 length cannot exceed 50 characters.");
        if(postalCode.length() > 10)
            throw new InputException("Postal code length cannot exceed 10 characters.");       
        if(phone.length() > 20)
            throw new InputException("Phone length cannot exceed 20 characters.");
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
    private void onActionUpdate(ActionEvent event) throws InputException {
        try {
            Customer customer = customerTbl.getSelectionModel().getSelectedItem();
            int customerId = customer.getCustomerId();
            int addressId = customer.getAddressId();
            int cityId = customer.getCityId();
            int countryId = customer.getCountryId();
            String customerName = customerNameTxt.getText();
            String address = customerAddressTxt.getText();
            String address2 = customerAddress2Txt.getText();
            String postalCode = postalCodeTxt.getText();
            String phone = customerPhoneTxt.getText();
            String city = cityComboBox.getSelectionModel().getSelectedItem();
            String country = countryComboBox.getSelectionModel().getSelectedItem();
            
            validateInput(customerName, address, address2, postalCode, phone);
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm update changes?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){    
                if(!customerName.isEmpty()){
                    String customerSqlStatement = "UPDATE customer "
                        + "SET customerName = " + "'" + customerName + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER()"
                        + "WHERE customerId = " + customerId + ";";
                    Query.makeQuery(customerSqlStatement);
                }
                
                if(!address.isEmpty()){
                    String addressSqlStatement = "UPDATE address "
                        + "SET address = " + "'" + address + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER() "
                        + "WHERE addressId = " + addressId + ";";
                    Query.makeQuery(addressSqlStatement);
                }
                
                if(!address2.isEmpty()){
                    String address2SqlStatement = "UPDATE address "
                        + "SET address2 = " + "'" + address2 + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER() "
                        + "WHERE addressId = " + addressId + ";";
                    Query.makeQuery(address2SqlStatement);
                }
                
                if(!postalCode.isEmpty()){
                    String postalCodeSqlStatement = "UPDATE address "
                        + "SET postalCode = " + "'" + postalCode + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER() "
                        + "WHERE addressId = " + addressId + ";";
                    Query.makeQuery(postalCodeSqlStatement);
                }
                
                if(!phone.isEmpty()){
                    String phoneSqlStatement = "UPDATE address "
                        + "SET phone = " + "'" + phone + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER() "
                        + "WHERE addressId = " + addressId + ";";
                    Query.makeQuery(phoneSqlStatement);
                }
                
                if(!city.isEmpty()){
                    String citySqlStatement = "UPDATE city "
                        + "SET city = " + "'" + city + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER() "
                        + "WHERE cityId = " + cityId + ";";
                    Query.makeQuery(citySqlStatement);
                }
                
                if(!country.isEmpty()){
                    String countrySqlStatement = "UPDATE country "
                        + "SET country = " + "'" + country + "', "
                        + "lastUpdate = CURRENT_TIMESTAMP(), "
                        + "lastUpdateBy = CURRENT_USER() "
                        + "WHERE countryId = " + countryId + ";";
                    Query.makeQuery(countrySqlStatement);
                }
                
                customerNameTxt.setText("");
                customerAddressTxt.setText("");
                customerAddress2Txt.setText("");
                postalCodeTxt.setText("");
                customerPhoneTxt.setText("");
                
                DataProvider.updateLocalCache();
            }
        } catch (InputException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error.");
            alert.setContentText(e + "");
            alert.showAndWait();
        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nothing selected.");
            alert.setContentText("Please select a customer from the table");
            alert.showAndWait();
        }
    }   

}
