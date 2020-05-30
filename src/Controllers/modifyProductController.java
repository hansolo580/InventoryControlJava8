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
import java.util.ResourceBundle;

import static Controllers.alertMessages.confirmationWindow;

public class modifyProductController implements Initializable {
    Inventory currentInventory;
    Product currentProduct;

    @FXML private TextField modifyProductID;
    @FXML private TextField modifyProductName;
    @FXML private TextField modifyProductStock;
    @FXML private TextField modifyProductPrice;
    @FXML private TextField modifyProductMax;
    @FXML private TextField modifyProductMin;
    @FXML private TableView<Part> associatedPartsTable;
    @FXML private TableView<Part> partSearchTable;
    @FXML private TextField partSearchInput;
    private ObservableList<Part> partInv = FXCollections.observableArrayList();
    private ObservableList<Part> partInvSearch = FXCollections.observableArrayList();
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    public modifyProductController(Inventory currentInventory, Product currentProduct) {
        this.currentInventory = currentInventory;
        this.currentProduct = currentProduct;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSearchTable();
        parseData();
    }

    private void loadSearchTable() {
        partInv.setAll(currentInventory.getAllParts());

        partSearchTable.setItems(partInv);
        partSearchTable.refresh();
    }

    @FXML private void modifyProductSearch(ActionEvent event) {
        for (Part i : currentInventory.getAllParts()) {
            if (i.getName().contains(partSearchInput.getText())) {
                partInvSearch.add(i);
            }
        }
        partSearchTable.setItems(partInvSearch);
    }

    public void changeScreenHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/mainView.fxml"));
        mainController controller = new mainController(currentInventory);

        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML public void modifyProductSave(ActionEvent event) throws IOException {
        TextField[] fieldcount = {modifyProductStock, modifyProductPrice, modifyProductMin, modifyProductMax};
        if (Integer.parseInt(modifyProductMin.getText()) > Integer.parseInt(modifyProductMax.getText())) {
            //AlertMessage.errorProduct(10, modifyProductMin);
        }

        saveProduct();
        changeScreenHome(event);
    }

    private void saveProduct() {
        if (Integer.parseInt(modifyProductStock.getText()) < Integer.parseInt(modifyProductMin.getText())) {
            alertMessages.errorCount(1, modifyProductStock);
        }
        if (Integer.parseInt(modifyProductStock.getText()) > Integer.parseInt(modifyProductMax.getText())) {
            alertMessages.errorCount(1, modifyProductStock);
        }

        Product newProduct = new Product(Integer.parseInt(modifyProductID.getText()), modifyProductName.getText(),
                Double.parseDouble(modifyProductPrice.getText()), Integer.parseInt(modifyProductStock.getText()),
                Integer.parseInt(modifyProductMin.getText()), Integer.parseInt(modifyProductMax.getText()));

        for (Part part : associatedPartsList) {
            newProduct.addAssociatedPart(part);
        }

        currentInventory.updateProduct(newProduct);
    }

    private void parseData() {
        for (int i = 0; i < 1000; i++) {
            Part partToFind = currentProduct.lookupAssociatedPart(i);
            if (partToFind != null) {
                associatedPartsList.add(partToFind);
            }
        }

        associatedPartsTable.setItems(associatedPartsList);

        this.modifyProductName.setText(currentProduct.getName());
        this.modifyProductID.setText(Integer.toString(currentProduct.getID()));
        this.modifyProductStock.setText(Integer.toString(currentProduct.getStock()));
        this.modifyProductPrice.setText(Double.toString(currentProduct.getPrice()));
        this.modifyProductMin.setText(Integer.toString(currentProduct.getMin()));
        this.modifyProductMax.setText(Integer.toString(currentProduct.getMax()));
    }

    @FXML private void removePart(ActionEvent event) {
        boolean confirm = confirmationWindow("Cancel?");
        Part removePart = associatedPartsTable.getSelectionModel().getSelectedItem();
        associatedPartsList.remove(removePart);
        associatedPartsTable.refresh();
    }

    @FXML private void addPart(ActionEvent event) {
        Part newPart = partSearchTable.getSelectionModel().getSelectedItem();
        associatedPartsList.add(newPart);
        associatedPartsTable.setItems(associatedPartsList);
    }

    @FXML private void returnHome(ActionEvent event) throws IOException {
        boolean confirm = confirmationWindow("Cancel?");
        changeScreenHome(event);
    }
}
