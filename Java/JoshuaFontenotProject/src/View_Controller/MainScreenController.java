/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import static Model.Inventory.deletePart;
import static Model.Inventory.lookupPart;
import static Model.Inventory.lookupProduct;
import Model.OutsourcedPart;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
public class MainScreenController implements Initializable {
    Stage stage;
    Parent scene;
    
    @FXML
    private Label titleLbl;
    @FXML
    private Label partsLbl;
    @FXML
    private Button partSearchBtn;
    @FXML
    private TextField partSearchTxt;
    @FXML
    private TableView<Part> partsTbl;
    @FXML
    private TableColumn<Part, Integer> partidCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInventoryLevelCol;
    @FXML
    private TableColumn<Part, Double> partCostCol;
    @FXML
    private Button partAddBtn;
    @FXML
    private Button partModifyBtn;
    @FXML
    private Button partDelBtn;
    @FXML
    private Label productsLbl;
    @FXML
    private Button productSearchBtn;
    @FXML
    private TextField productSearchTxt;
    @FXML
    private TableView<Product> productsTbl;
    @FXML
    private TableColumn<Product, Integer> productIDCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevelCol;
    @FXML
    private TableColumn<Product, Double> productCostCol;
    @FXML
    private Button productAddBtn;
    @FXML
    private Button productModBtn;
    @FXML
    private Button productDelBtn;
    @FXML
    private Button exitBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partsTbl.setItems(Inventory.getAllParts());
        partidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productsTbl.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }    

    @FXML
    private void onActionSearchParts(ActionEvent event) throws IOException {
        String partList = partSearchTxt.getText();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ShowSearchPart.fxml"));
        loader.load();
        
        ShowSearchPartController SSPController = loader.getController();
        SSPController.sendList(Inventory.lookupPart(partList));
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartInhouse.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionModPart(ActionEvent event) throws IOException {
        Part part = partsTbl.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        
        if(part instanceof InHousePart){
            loader.setLocation(getClass().getResource("/View_Controller/ModifyPartInHouse.fxml"));
            loader.load();
        
            ModifyPartInHouseController MPController = loader.getController();
            MPController.sendPart(partsTbl.getSelectionModel().getSelectedItem());
        
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

        else if(part instanceof OutsourcedPart){
            loader.setLocation(getClass().getResource("/View_Controller/ModifyPartOutsourced.fxml"));
            loader.load();
        
            ModifyPartOutsourcedController MPOController = loader.getController();
            MPOController.sendPart(partsTbl.getSelectionModel().getSelectedItem());
        
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    private void onActionDelPart(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selection?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            Inventory.deletePart(partsTbl.getSelectionModel().getSelectedItem());
        }  
    }

    @FXML
    private void onActionSearchProducts(ActionEvent event) throws IOException {
        String productList = productSearchTxt.getText();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ShowSearchProduct.fxml"));
        loader.load();
        
        ShowSearchProductController SSPController = loader.getController();
        SSPController.sendList(Inventory.lookupProduct(productList));
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionModProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
        loader.load();
        
        ModifyProductController MPController = loader.getController();
        MPController.sendProduct(productsTbl.getSelectionModel().getSelectedItem());
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionDelProduct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selection?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            Inventory.deleteProduct(productsTbl.getSelectionModel().getSelectedItem());
        } 
    }

    @FXML
    private void onActionExit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.exit(0);
        } 
    }

    private void onActionDisplayPart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/DisplayPart.fxml"));
        loader.load();
        
        DisplayPartController DPSController = loader.getController();
        DPSController.sendPart(partsTbl.getSelectionModel().getSelectedItem());
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    } 
}
