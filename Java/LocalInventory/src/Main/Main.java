/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joshuafontenotproject;

import Model.InHousePart;
import Model.Inventory;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Josh
 */
public class JoshuaFontenotProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/Main Screen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*InHousePart part1 = new InHousePart(0, "part1", 9.99, 1, 0, 100, 0);
        InHousePart part2 = new InHousePart(1, "part2", 19.99, 2, 0, 100, 1);
        Product product1 = new Product(0, "product1", 10.99, 1, 0, 399);
        
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addProduct(product1);*/
        
        launch(args);
    }
    
}