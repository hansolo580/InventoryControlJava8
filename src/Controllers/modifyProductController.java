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

public class modifyProductController {
    Inventory currentInventory;
    public TextField modifyProductID;
    public TextField modifyProductName;
    public TextField modifyProductStock;
    public TextField modifyProductPrice;
    public TextField modifyProductMax;
    public TextField modifyProductMin;
    public TextField modifyProductSearchField;

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

    public void modifyProductDelete(ActionEvent actionEvent) {
    }

    public void modifyProductSave(ActionEvent actionEvent) {
    }

    public void modifyProductAdd(ActionEvent actionEvent) {
    }

    public void modifyProductSearch(ActionEvent actionEvent) {
    }
}
