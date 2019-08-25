/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class DisplayProductController implements Initializable {
    Stage stage;
    Parent scene;

    private TableView<Product> searchTbl;
    private TableColumn<Product, Integer> idCol;
    private TableColumn<Product, String> nameCol;
    private TableColumn<Product, Integer> inventoryCol;
    private TableColumn<Product, Double> unitCostCol;
    @FXML
    private Button backBtn;
    @FXML
    private Label idLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private Label priceLbl;
    @FXML
    private Label invLbl;
    @FXML
    private Label machineIdLbl;
    @FXML
    private Label idTxtLbl;
    @FXML
    private Label nameTxtLbl;
    @FXML
    private Label priceTxtLbl;
    @FXML
    private Label invTxtLbl;
    @FXML
    private Label machineTxtLbl;
    @FXML
    private Label companyNameLbl;
    @FXML
    private Label companyTxtLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchTbl.setItems(Inventory.getAllProducts());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        unitCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }    

    @FXML
    private void onActionBackBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/Main Screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
