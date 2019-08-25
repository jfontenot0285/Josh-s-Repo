/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Josh
 */
public abstract class Part {
    private String name;
    private static int pId;
    private int id;
    private int stock, min, max;
    private double price;
    public Part(int id, String name, double price, int stock, int min, int max){
        pId++;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public static void setpId(int pId) {
        Part.pId = pId;
    }

    public static int getpId() {
        return pId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
