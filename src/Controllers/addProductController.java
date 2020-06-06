package Controllers;

import Models.Inventory;
import Models.Part;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static Controllers.alertMessages.confirmationWindow;

public class addProductController implements Initializable {

    Inventory currentInventory;

    @FXML private TextField NewProductID;
    @FXML private TextField NewProductName;
    @FXML private TextField NewProductStock;
    @FXML private TextField NewProductPrice;
    @FXML private TextField NewProductMax;
    @FXML private TextField NewProductMin;
    @FXML private TextField addProductSearchField;
    @FXML private TableView<Part> partSearchTableView;
    @FXML private TableView<Part> associatedPartsTableView;
    private ObservableList<Part> partInv = FXCollections.observableArrayList();
    private ObservableList<Part> partInvSearch = FXCollections.observableArrayList();
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    public addProductController(Inventory currentInventory) { this.currentInventory = currentInventory;}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateProductID();
        loadSearchTable();
    }

    private void generateProductID() {
        boolean match;
        Random randomNum = new Random();
        Integer num = randomNum.nextInt(1000);

        if (currentInventory.productInvSize() == 0) {
            NewProductID.setText(num.toString());
        }
        else {
            match = numCreator(num);

            if (!match) {
                NewProductID.setText(num.toString());
            }
            else {
                generateProductID();
            }
        }
    }

    private boolean numCreator(Integer num) {
        Part match = currentInventory.lookupPart(num);
        return match != null;
    }

    private void loadSearchTable() {
        partInv.setAll(currentInventory.getAllParts());

        partSearchTableView.setItems(partInv);
        partSearchTableView.refresh();
    }

    @FXML public void cancel(ActionEvent event) throws IOException {
        boolean confirm = confirmationWindow("Cancel?");
        if (confirm) {
            changeScreenHome(event);
        }
    }

    @FXML public void changeScreenHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/mainView.fxml"));
        mainController controller = new mainController(currentInventory);

        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML private void addProductSearch(ActionEvent actionEvent) {
        if (!addProductSearchField.getText().trim().isEmpty()) {
            partInv.clear();
            partSearchTableView.setItems(currentInventory.lookupPart(addProductSearchField.getText().trim()));
            partSearchTableView.refresh();
        }
    }

    @FXML private void addPart(ActionEvent actionEvent) {
        Part newPart = partSearchTableView.getSelectionModel().getSelectedItem();
        boolean duplicatePart = false;

        if (newPart != null) {
            int newPartID = newPart.getID();
            for (Part part : associatedPartsList) {
                if (part.getID() == newPartID) {
                    alertMessages.errorCount(5, NewProductID);
                    duplicatePart = true;
                }
            }
        }

        if (!duplicatePart) {
            associatedPartsList.add(newPart);
        }

        associatedPartsTableView.setItems(associatedPartsList);
    }

    @FXML private void removePart(ActionEvent actionEvent) {
        boolean confirm = confirmationWindow("Cancel?");
        Part removePart = associatedPartsTableView.getSelectionModel().getSelectedItem();
        if (removePart != null) {
            associatedPartsList.remove(removePart);
            associatedPartsTableView.refresh();
        }
    }


    @FXML private void addProductAdd(ActionEvent actionEvent) throws IOException {
        boolean end = false;
        TextField[] fieldCount = {NewProductStock, NewProductPrice, NewProductMin, NewProductMax};
        if (Integer.parseInt(NewProductMin.getText()) > Integer.parseInt(NewProductMax.getText())) {
            //AlertMessage.errorProduct(1, NewProductMin);
            return;
        }

        addProductSave();
        changeScreenHome(actionEvent);
    }

    public void addProductSave() {
        double minCost = 0;
        for (Part value : associatedPartsList) {
            minCost += value.getPrice();
        }
        if (Double.parseDouble(NewProductPrice.getText()) < minCost) {
            alertMessages.errorCount(4, NewProductPrice);
        }
        else if (Integer.parseInt(NewProductStock.getText()) < Integer.parseInt(NewProductMin.getText())) {
            alertMessages.errorCount(1, NewProductStock);
        }
        else if (Integer.parseInt(NewProductStock.getText()) > Integer.parseInt(NewProductMax.getText())) {
            alertMessages.errorCount(1, NewProductStock);
        }
        else if (NewProductName.getText().isEmpty()) {
            alertMessages.errorCount(3, NewProductName);
        }
        else if (Double.parseDouble(NewProductPrice.getText()) <= 0){
            alertMessages.errorCount(7, NewProductPrice);
        }
        else if (associatedPartsList.isEmpty()) {
            alertMessages.errorCount(6, NewProductID);
        }
        else {
            Product newProduct = new Product(Integer.parseInt(NewProductID.getText()), NewProductName.getText(),
                    Double.parseDouble(NewProductPrice.getText()), Integer.parseInt(NewProductStock.getText()),
                    Integer.parseInt(NewProductMin.getText()), Integer.parseInt(NewProductMax.getText()));
            for (Part part : associatedPartsList) {
                newProduct.addAssociatedPart(part);
            }

            currentInventory.addProduct(newProduct);
        }
    }
}
