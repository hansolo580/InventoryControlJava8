package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class modifyProductController {
    public TextField modifyProductID;
    public TextField modifyProductName;
    public TextField modifyProductStock;
    public TextField modifyProductPrice;
    public TextField modifyProductMax;
    public TextField modifyProductMin;
    public TextField modifyProductSearchField;

    public void changeScreenHome(ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("/Views/mainView.fxml"));
        Scene homeScene = new Scene(homeParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(homeScene);
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
