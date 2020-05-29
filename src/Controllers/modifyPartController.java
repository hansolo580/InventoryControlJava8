package Controllers;

import Models.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class modifyPartController {
    Inventory currentInventory;
    public TextField modifyPartID;
    public TextField modifyPartName;
    public TextField modifyPartPrice;
    public TextField modifyPartStock;
    public TextField modifyPartMax;
    public TextField modifyPartFlex;
    public TextField modifyPartMin;
    public ToggleGroup partSourceToggleGroup;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/mainView.fxml"));
        mainController controller = new mainController(currentInventory);

        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void modifyPartSave(ActionEvent actionEvent) {
    }
}
