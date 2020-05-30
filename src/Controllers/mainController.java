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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    Inventory currentInventory;

    @FXML private TableView<Part> mainPartsTableView;
    @FXML private TableView<Product> tableProducts;
    private ObservableList<Part> partInv = FXCollections.observableArrayList();
    private ObservableList<Product> productInv = FXCollections.observableArrayList();
    @FXML private TextField partSearchInput;
    @FXML private TextField productSearchInput;
    private ObservableList<Part> partSearchList = FXCollections.observableArrayList();
    private ObservableList<Product> productSearchList = FXCollections.observableArrayList();

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

        mainPartsTableView.setItems(partInv);
        mainPartsTableView.refresh();
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

    public void changeScreenAddPart(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/addPartView.fxml"));
            addPartController controller = new addPartController(currentInventory);

            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {

        }
    }

    // This changes to ModifyPartView

    public void changeScreenModifyPart(ActionEvent event) {
        try {
            Part selectedPart = mainPartsTableView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/modifyPartView.fxml"));
            modifyPartController controller = new modifyPartController(currentInventory, selectedPart);

            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {

        }
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
        Product selectedProduct = tableProducts.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/modifyProductView.fxml"));
        modifyProductController controller = new modifyProductController(currentInventory, selectedProduct);

        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML private void lookUpPart(ActionEvent event) {
        {mainPartsTableView.setItems(currentInventory.lookupPart(partSearchInput.getText()));
        mainPartsTableView.refresh();}
    }

    @FXML private void removePart(ActionEvent event) {
        Part removePart = mainPartsTableView.getSelectionModel().getSelectedItem();
        currentInventory.deletePart(removePart.getID());
        partInv.remove(removePart);
        mainPartsTableView.refresh();
    }

    @FXML private void lookUpProduct(ActionEvent event) {
        {tableProducts.setItems(currentInventory.lookupProduct(productSearchInput.getText()));
        tableProducts.refresh();}
    }

    @FXML private void removeProduct(ActionEvent event) {
        Product removeProduct = tableProducts.getSelectionModel().getSelectedItem();
        currentInventory.deleteProduct(removeProduct.getID());
        productInv.remove(removeProduct);
        tableProducts.refresh();
    }

    private void errorWindow(int code) {
        if (code == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Whoops");
            alert.setHeaderText("Something went wrong!");
            alert.setContentText("Isn't this error message's vagueness super helpful?");
            alert.showAndWait();
        }
    }

}
