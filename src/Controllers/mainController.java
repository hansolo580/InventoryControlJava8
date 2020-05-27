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

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    Inventory currentInventory;

    @FXML private TableView<Part> tableParts;
    @FXML private TableView<Product> tableProducts;
    private ObservableList<Part> partInv = FXCollections.observableArrayList();
    private ObservableList<Product> productInv = FXCollections.observableArrayList();
    @FXML private TextField partSearchInput;
    @FXML private TextField productSearchInput;

    public mainController(Inventory currentInventory) {
        this.currentInventory = currentInventory;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadParts();
        loadProducts();
    }

    private void loadParts() {
        partInv.setAll(currentInventory.getAllParts());

        tableParts.setItems(partInv);
        tableParts.refresh();
    }

    private void loadProducts() {
        productInv.setAll(currentInventory.getAllProducts());

        tableProducts.setItems(productInv);
        tableProducts.refresh();
    }

    @FXML
    private void Quit(ActionEvent event) {
        System.exit(0);
    }

    public void changeScreenAddPart(ActionEvent event) throws IOException {
        Parent newPartParent = FXMLLoader.load(getClass().getResource("/Views/AddPartView.fxml"));
        Scene newPartScene = new Scene(newPartParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newPartScene);
        window.show();
    }

    // This changes to ModifyPartView

    public void changeScreenModifyPart(ActionEvent event) throws IOException {
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("/Views/ModifyPartView.fxml"));
        Scene modifyPartScene = new Scene(modifyPartParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(modifyPartScene);
        window.show();
    }

    //Changes to AddProductView

    public void changeScreenAddProduct(ActionEvent event) throws IOException {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/Views/AddProductView.fxml"));
        Scene addProductScene = new Scene(addProductParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(addProductScene);
        window.show();
    }

    //Changes to ModifyProductView

    public void changeScreenModifyProduct(ActionEvent event) throws IOException {
        Parent modifyProductParent = FXMLLoader.load(getClass().getResource("/Views/ModifyProductView.fxml"));
        Scene modifyProductScene = new Scene(modifyProductParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(modifyProductScene);
        window.show();
    }

    @FXML private void lookUpPart(ActionEvent event) {
        {tableParts.setItems(currentInventory.lookupPart(partSearchInput.getText()));
        tableParts.refresh();}
    }

    @FXML private void lookUpProduct(ActionEvent event) {
        {tableProducts.setItems(currentInventory.lookupProduct(productSearchInput.getText()));
        tableProducts.refresh();}
    }
}
