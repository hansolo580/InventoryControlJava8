package Controllers;

import Models.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class addProductController {
    Inventory currentInventory;
    public TextField NewProductID;
    public TextField NewProductName;
    public TextField NewProductStock;
    public TextField NewProductPrice;
    public TextField NewProductMax;
    public TextField NewProductMin;
    public TextField addProductSearchField;

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

    public void addProductSearch(ActionEvent actionEvent) {
    }

    public void addProductAdd(ActionEvent actionEvent) {
    }

    public void addProductDelete(ActionEvent actionEvent) {
    }

    public void addProductSave(ActionEvent actionEvent) {
    }
}
