package Models;

import com.sun.webkit.SimpleSharedBufferInputStream;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Product {

    private ArrayList<Part> associatedParts = new ArrayList<Part>();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    //Constructor
    public Product(int ID, String name, double price, int stock, int min, int max) {
        setID(ID);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
    }

    //Setters
    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
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

    //Getters

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    //Other methods
    public void addAssociatedPart(Part newPart){
        associatedParts.add(newPart);
    }

    public boolean deleteAssociatedPart(int byePart){
        for (int i = 0; i < associatedParts.size(); i++) {
            if (associatedParts.get(i).getID() == byePart) {
                associatedParts.remove(i);
                return true;
            }
        }

        return false;
    }

    public Part lookupAssociatedPart(int partToSearch) {
        for (Part associatedPart : associatedParts) {
            if (associatedPart.getID() == partToSearch) {
                return associatedPart;
            }
        }
        return null;
    }

    public ArrayList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public int getAssociatedPartsSize() {return associatedParts.size();}
}
