package Models;

import com.sun.webkit.SimpleSharedBufferInputStream;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private final IntegerProperty id;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty stock;
    private final IntegerProperty min;
    private final IntegerProperty max;

    //Constructor
    public Product(int ID, String name, double price, int Stock, int min, int max) {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.stock = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
    }

    public IntegerProperty productID() {
        return id;
    }

    public StringProperty productName() {
        return name;
    }

    public DoubleProperty productPrice() {
        return price;
    }

    public IntegerProperty productStock() {
        return stock;
    }

    //Setters
    public void setID(int id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    //Getters

    public int getID() {
        return this.id.get();
    }

    public String getName() {
        return this.name.get();
    }

    public double getPrice() {
        return this.price.get();
    }

    public int getStock() {
        return this.stock.get();
    }

    public int getMin() {
        return this.min.get();
    }

    public int getMax() {
        return this.max.get();
    }

    //Other methods
    public void addAssociatedPart(Part newPart){
        associatedParts.add(newPart);
    }
    public boolean deleteAssociatedPart(ObservableList<Part> part){
        return false;
    }

    public static ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
