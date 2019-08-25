/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Josh
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    public static Part lookupPart(int partID){
        return allParts.get(partID);
    }
    public static Product lookupProduct(int productID){
        return allProducts.get(productID);
    }
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        for(Part part : getAllParts()) {
            if(part.getName().contains(partName))
                tempList.add(part);
        }
        if(tempList.isEmpty())
            return allParts;
        else
            return tempList;
    }
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        for(Product product : getAllProducts()) {
            if(product.getName().contains(productName))
                tempList.add(product);
        }
        if(tempList.isEmpty())
            return allProducts;
        else
            return tempList;
    }
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    public static void deletePart(Part selectedPart){
        allParts.remove(selectedPart);
    }
    public static void deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
