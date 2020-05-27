package Models;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> partInv = FXCollections.observableArrayList();
    private static ObservableList<Product> productInv = FXCollections.observableArrayList();
    // private final Part newPart;
    //private final Product newProduct;

    public Inventory() {
    }
    //adds

    public static void addPart(Part newPart) {
        partInv.add(newPart);
    }

    public void addProduct(Product newProduct) {
        productInv.add(newProduct);
    }

    //lookups

    public Part lookupPart(int partId) {
        for (int i = 0; i < partInv.size(); i++) {
            if (partInv.get(i).getID() == partId) {
                return partInv.get(i);
            }
        }
        return null;
    }

    public Product lookupProduct(int productId) {
        for (int i = 0; i < productInv.size(); i++) {
            if (productInv.get(i).getID() == productId) {
                return productInv.get(i);
            }
        }
        return null;
    }

    public ObservableList<Part> lookupPart(String partName) {
        ObservableList partSearchResults = FXCollections.observableArrayList();
        for (Part j : getAllParts()) {
            if (j.getName().contains(partName)) {
                partSearchResults.add(j);
            }
            return partSearchResults;
        }
        return null;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList productSearchResults = FXCollections.observableArrayList();
        for (Product j: getAllProducts()) {
            if (j.getName().contains(productName)) {
                productSearchResults.add(j);
            }
            return productSearchResults;
        }
        return null;
    }

    //updates

    public void updatePart(int index, Part selectedPart) {
        partInv.set(index, selectedPart);
    }

    public void updateProduct(int index, Product newProduct) {
        productInv.set(index, newProduct);
    }

    //deletes

    public boolean deletePart(Part selectedPart) {
        partInv.remove(selectedPart);
        return true;
    }

    public boolean deleteProduct(Product selectedProduct) {
        productInv.remove(selectedProduct);
        return true;
    }

    //gets

    public ObservableList<Part> getAllParts(){
        return partInv;
    }

    public ObservableList<Product> getAllProducts(){
        return productInv;
    }


}
