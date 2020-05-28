package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class addProductController {
    public TextField NewProductID;
    public TextField NewProductName;
    public TextField NewProductStock;
    public TextField NewProductPrice;
    public TextField NewProductMax;
    public TextField NewProductMin;
    public TextField addProductSearchField;

    public void changeScreenHome(ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("/Views/mainView.fxml"));
        Scene homeScene = new Scene(homeParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(homeScene);
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
