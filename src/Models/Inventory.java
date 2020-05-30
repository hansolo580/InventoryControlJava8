package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Part> partInv;
    private ArrayList<Product> productInv;

    public Inventory() {
        partInv = new ArrayList<>();
        productInv = new ArrayList<>();
    }
    //adds

    public void addPart(Part newPart) {
            this.partInv.add(newPart);
    }

    public void addProduct(Product newProduct) {
        this.productInv.add(newProduct);
    }

    //lookups

    public Part lookupPart(int partId) {
        for (Part part : partInv) {
            if (part.getID() == partId) {
                return part;
            }
        }
        return null;
    }

    public Product lookupProduct(int productId) {
        for (Product product : productInv) {
            if (product.getID() == productId) {
                return product;
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

    public boolean deletePart(int selectedPart) {
        for (int i = 0; i < partInv.size(); i++) {
            if (partInv.get(i).getID() == selectedPart) {
                partInv.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int selectedProduct) {
        for (int i = 0; i < productInv.size(); i++) {
            if (productInv.get(i).getID() == selectedProduct) {
                productInv.remove(i);
                return true;
            }
        }
        return false;
    }

    //gets

    public ArrayList<Part> getAllParts(){
        return partInv;
    }

    public ArrayList<Product> getAllProducts(){
        return productInv;
    }

    //search

    public Part lookUpPart(int selectedPart) {
        for (Part part : partInv) {
            if (part.getID() == selectedPart) {
                return part;
            }
        }
        return null;
    }

    public ObservableList<Part> lookUpPart(String selectedPart) {
        ObservableList lookUpListParts = FXCollections.observableArrayList();
        for (Part i : getAllParts()) {
            if (i.getName().contains(selectedPart)) {
                lookUpListParts.add(i);
            }
        }
        return lookUpListParts;
    }

    public Product lookUpProduct(int selectedProduct) {
        for (Product product : productInv) {
            if (product.getID() == selectedProduct) {
                return product;
            }
        }
        return null;
    }

    public ObservableList<Product> lookUpProduct(String selectedProduct) {
        return null;
    }

    //updates

    public void updatePart(Part selectedPart) {
        for (int i = 0; i < partInv.size(); i++) {
            if (partInv.get(i).getID() == selectedPart.getID()) {
                partInv.set(i, selectedPart);
                return;
            }
        }
    }

    public void updateProduct(Product selectedProduct) {
        for (int i = 0; i < productInv.size(); i++) {
            if (productInv.get(i).getID() == selectedProduct.getID()) {
                productInv.set(i, selectedProduct);
                return;
            }
        }
    }

    public int partInvSize() {return partInv.size();}

    public int productInvSize() {return productInv.size();}

}
