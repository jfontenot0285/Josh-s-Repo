/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import static Model.Inventory.lookupPart;
import Model.OutsourcedPart;
import Model.Part;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class ModifyPartInHouseController implements Initializable {
    
    Stage stage;
    Parent scene;

    @FXML
    private RadioButton InHouse;
    @FXML
    private ToggleGroup group1;
    @FXML
    private RadioButton Outsourced;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField machineidTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label idLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private Label invLbl;
    @FXML
    private Label maxLbl;
    @FXML
    private Label minLbl;
    @FXML
    private Label machineidLbl;
    @FXML
    private TextField priceTxt;
    @FXML
    private Label priceLbl;
    @FXML
    private Label storeIndexLbl;
    
    public int searchIndex(int id){
        int index = -1;
        for(Part part : Inventory.getAllParts()){
            index++;
            if(part.getId() == id)
                return index;
        }
        return 0;
    }

    public void sendPart(Part part){
        idTxt.setText(String.valueOf(part.getId()));
        nameTxt.setText(part.getName());
        priceTxt.setText(String.valueOf(part.getPrice()));
        invTxt.setText(String.valueOf(part.getStock()));
        minTxt.setText(String.valueOf(part.getMin()));
        maxTxt.setText(String.valueOf(part.getMax()));

        if(part instanceof InHousePart)
            machineidTxt.setText(String.valueOf(((InHousePart) part).getMachineId()));
        
        storeIndexLbl.setText(String.valueOf(searchIndex(part.getId())));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void onActionSave(ActionEvent event) throws IOException {
        try{
            int id = Integer.parseInt(idTxt.getText());
            String name = nameTxt.getText();
            int machineId = Integer.parseInt(machineidTxt.getText());
            int min = Integer.parseInt(minTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());
            int stock = Integer.parseInt(invTxt.getText());
            double price = Double.parseDouble(priceTxt.getText());
            int index = Integer.parseInt(storeIndexLbl.getText());
            
            if(max < min)
                throw new IllegalArgumentException();

            InHousePart part = (InHousePart)Inventory.lookupPart(id);
            part.setName(name);
            part.setMin(min);
            part.setMax(max);
            part.setStock(stock);
            part.setPrice(price);
            part.setMachineId(machineId);
        
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
    private void onActionOutsourcedRadio(ActionEvent event) throws IOException {
        int partId = Integer.parseInt(idTxt.getText());
        Part part = Inventory.lookupPart(partId);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ModifyPartOutsourced.fxml"));
        loader.load();
        
        ModifyPartOutsourcedController MPOController = loader.getController();
        MPOController.sendPart(part);
        
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionInhouseRadio(ActionEvent event) {
    }
    
}
