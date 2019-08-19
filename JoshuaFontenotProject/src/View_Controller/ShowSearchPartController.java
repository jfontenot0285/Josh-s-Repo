/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class ShowSearchPartController implements Initializable {
    Stage stage;
    Parent scene;
    
    public void sendList(ObservableList<Part> list){
        tableSearchResults.setItems(list);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        invCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        minCol.setCellValueFactory(new PropertyValueFactory<>("min"));
        maxCol.setCellValueFactory(new PropertyValueFactory<>("max"));
        machineCol.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        companyCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
    }

    @FXML
    private TableView<Part> tableSearchResults;
    @FXML
    private TableColumn<Part, Integer> idCol;
    @FXML
    private TableColumn<Part, String> nameCol;
    @FXML
    private TableColumn<Part, Double> priceCol;
    @FXML
    private TableColumn<Part, Integer> invCol;
    @FXML
    private TableColumn<Part, Integer> minCol;
    @FXML
    private TableColumn<Part, Integer> maxCol;
    @FXML
    private TableColumn<Part, Integer> machineCol;
    @FXML
    private TableColumn<Part, String> companyCol;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/Main Screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
