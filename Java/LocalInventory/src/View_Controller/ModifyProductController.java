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
public class ModifyProductController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    @FXML
    private Label idLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private Label invLbl;
    @FXML
    private Label priceLbl;
    @FXML
    private Label maxLbl;
    @FXML
    private Label minLbl;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField searchTxt;
    @FXML
    private Button addBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button delBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private TableView<Part> partsTbl;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInvCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private Label storeIndexLbl;
    @FXML
    private TableView<Part> asPartsTbl;
    @FXML
    private TableColumn<Part, Integer> asPartIdCol;
    @FXML
    private TableColumn<Part, String> asPartNameCol;
    @FXML
    private TableColumn<Part, Integer> asInvCol;
    @FXML
    private TableColumn<Part, Double> asPriceCol;

    /*public int searchIndex(int id){
    int index = -1;
    for(Product product : Inventory.getAllProducts()){
    index++;
    if(product.getId() == id)
    return index;
    }
    return 0;
    }*/

    public void sendProduct(Product product){
        idTxt.setText(String.valueOf(product.getId()));
        nameTxt.setText(product.getName());
        priceTxt.setText(String.valueOf(product.getPrice()));
        invTxt.setText(String.valueOf(product.getStock()));
        minTxt.setText(String.valueOf(product.getMin()));
        maxTxt.setText(String.valueOf(product.getMax()));
        
        partsTbl.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        asPartsTbl.setItems(product.getAssociatedParts());
        asPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        asPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        asInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        asPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    private void onActionSearch(ActionEvent event) throws IOException {
        String productList = searchTxt.getText();
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

    private void onActionAdd(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartInHouseAssociated.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private void onActionSave(ActionEvent event) throws IOException {
        try{
            int id = Integer.parseInt(idTxt.getText());
            String name = nameTxt.getText();
            int min = Integer.parseInt(minTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());
            int stock = Integer.parseInt(invTxt.getText());
            double price = Double.parseDouble(priceTxt.getText());
            
            if(max < min){
                throw new IllegalArgumentException();
            }
            
            Product product = Inventory.lookupProduct(id);
            product.setName(name);
            product.setMin(min);
            product.setMax(max);
            product.setStock(stock);
            product.setPrice(price);
            
            /*Product product = Inventory.lookupProduct(Product.getpId());
            product.addAssociatedPart(part);*/
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
    private void onActionAssociate(ActionEvent event) throws IOException {
        Part part = partsTbl.getSelectionModel().getSelectedItem();
        int index = Integer.parseInt(idTxt.getText());
        Product product = Inventory.lookupProduct(index);
        product.addAssociatedPart(part);
        
        asPartsTbl.setItems(product.getAssociatedParts());
        asPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        asPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        asInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        asPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void onActionDisassociate(ActionEvent event) {
        int index = Integer.parseInt(idTxt.getText());
        Product delProduct = Inventory.lookupProduct(index);
        Part part = asPartsTbl.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to disassociate the selected part?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            delProduct.deleteAssociatedPart(part);
        }
    }
}
