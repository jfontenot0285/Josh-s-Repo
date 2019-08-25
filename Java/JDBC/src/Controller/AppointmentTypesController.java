/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.Query;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class AppointmentTypesController implements Initializable {
    
    ObservableList<Appointment> typeCountList = FXCollections.observableArrayList();
    Stage stage;
    Parent scene;
    @FXML
    private TableColumn<Appointment, String> monthCol;
    @FXML
    private Button backBtn;
    @FXML
    private TableView<Appointment> typeTbl;
    @FXML
    private TableColumn<Appointment, Integer> typeCountCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    
    String getMonthName(int i){
        switch (i) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            default:
                return "December";
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeCountList.clear();     
        String sqlStatement = "SELECT COUNT(type), type, MONTH(start) "
                + "from appointment "
                + "GROUP BY MONTH(start);";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        
        try {
            while(result.next()){
                int typeCount = result.getInt("COUNT(type)");
                String type = result.getString("type");
                String startMonth = result.getString("MONTH(start)");
                String nameMonth = getMonthName(Integer.parseInt(startMonth));
                Appointment appointment = new Appointment(typeCount, type, nameMonth);
                typeCountList.add(appointment); 
            }
        } catch (SQLException ex) {
        Logger.getLogger(UpdateRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        typeTbl.setItems(typeCountList);
        typeCountCol.setCellValueFactory(new PropertyValueFactory<>("typeCount"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));   
    }    

    @FXML
    private void onActionBack(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Controller/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
