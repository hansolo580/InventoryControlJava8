package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainController {
    @FXML
    void Quit(ActionEvent event) {
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
}
