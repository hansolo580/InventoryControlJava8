package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class modifyPartController {
    @FXML
    private Label partLabel;
    @FXML
    void AddPartButtonInternal(ActionEvent event) {
        partLabel.setText("Machine ID");
    }
    @FXML void AddPartButtonOutsourced(ActionEvent event) {
        partLabel.setText("Company Name");
    }

    public void changeScreenHome(ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("/Views/mainView.fxml"));
        Scene homeScene = new Scene(homeParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(homeScene);
        window.show();
    }
}
