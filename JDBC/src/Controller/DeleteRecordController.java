/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
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
public class DeleteRecordController implements Initializable {

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
    private Button deleteBtn;
    
    Stage stage;
    Parent scene;
    
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateCustomerTbl();
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
        Customer customer = customerTbl.getSelectionModel().getSelectedItem();
        int customerId = customer.getCustomerId();
        String sqlStatement = "DELETE FROM customer WHERE customerId = " +
                    "'" + customerId + "';";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove the selected customer from the database?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            Query.makeQuery(sqlStatement);
            DataProvider.deleteCustomer(customer);
            DataProvider.updateLocalCache();
        }
    }
    
}
