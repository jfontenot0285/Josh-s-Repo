/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.Part;
import Model.Product;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class AddPartInHouseController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private RadioButton InHouse;
    @FXML
    private ToggleGroup tog1;
    @FXML
    private RadioButton Outsourced;
    @FXML
    private TextField idFieldTxt;
    @FXML
    private TextField nameFieldTxt;
    @FXML
    private TextField machineIDTxt;
    @FXML
    private TextField minFieldTxt;
    @FXML
    private TextField maxFieldTxt;
    @FXML
    private TextField invFieldTxt;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField priceFieldTxt;
    
    public void sendProduct(Product product){
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionInhouseRadio(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartInhouse.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionOutsourcedRadio(ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartOutsourced.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionIdField(ActionEvent event) {
    }

    @FXML
    private void onActionNameField(ActionEvent event) {
    }

    @FXML
    private void onActionMachineID(ActionEvent event) {
    }

    @FXML
    private void onActionMinField(ActionEvent event) {
    }

    @FXML
    private void onActionMaxField(ActionEvent event) {
    }

    @FXML
    private void onActionInvField(ActionEvent event) {
    }

    @FXML
    private void onActionSave(ActionEvent event) throws IOException {
        
        try{
            String name = nameFieldTxt.getText();
            int machineId = Integer.parseInt(machineIDTxt.getText());
            int min = Integer.parseInt(minFieldTxt.getText());
            int max = Integer.parseInt(maxFieldTxt.getText());
            int stock = Integer.parseInt(invFieldTxt.getText());
            double price = Double.parseDouble(priceFieldTxt.getText());
            
            if(max < min){
                throw new IllegalArgumentException();
            }
        
            Inventory.addPart(new InHousePart(Part.getpId(), name, price, stock, min, max, machineId));
        
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/Main Screen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Enter a valid value for each text field");
            alert.showAndWait();
        } catch(IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Maximum value cannot be less than minimum value");
            alert.showAndWait();
        }
    }

    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/Main Screen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }   
    }

    @FXML
    private void onActionPriceField(ActionEvent event) {
    }
    
}
