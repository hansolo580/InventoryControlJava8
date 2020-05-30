package Controllers;

import Models.InHouse;
import Models.Inventory;
import Models.Outsourced;
import Models.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class addPartController implements Initializable {

    public ToggleGroup partSourceToggleGroup;
    Inventory currentInventory;

    @FXML private Label partLabel;
    @FXML void AddPartButtonInternal(ActionEvent event) {
        partLabel.setText("Machine ID");
    }
    @FXML void AddPartButtonOutsourced(ActionEvent event) {
        partLabel.setText("Company Name");
    }
    @FXML private TextField NewIDField;
    @FXML private TextField NewNameField;
    @FXML private TextField NewStockField;
    @FXML private TextField NewPriceField;
    @FXML private TextField NewMinField;
    @FXML private TextField NewMaxField;
    @FXML private TextField NewFlexField;
    @FXML private RadioButton radioInternal;
    @FXML private RadioButton radioExternal;

    public addPartController(Inventory currentInventory) {
        this.currentInventory = currentInventory;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generatePartID();
        setFields();
    }

    private void setFields() {
        NewIDField.setText("Part ID");
        NewNameField.setText("Part Name");
        NewStockField.setText("Current Inventory");
        NewPriceField.setText("Price");
        NewMinField.setText("Min");
        NewMaxField.setText("Max");
        NewFlexField.setText("Company");
        partLabel.setText("Company");
        radioExternal.setSelected(true);
    }

    private void generatePartID() {
        boolean match;
        Random randomNum = new Random();
        Integer num = randomNum.nextInt(1000);

        if (currentInventory.partInvSize() == 0) {
            NewIDField.setText(num.toString());
        }
        else {
            match = findIfTaken(num);

            if (!match) {
                NewIDField.setText(num.toString());
            }
            else {
                generatePartID();
            }
        }
    }

    private boolean findIfTaken(Integer num) {
        Part match = currentInventory.lookupPart(num);
        return match != null;
    }

    public void changeScreenHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/mainView.fxml"));
            mainController controller = new mainController(currentInventory);

            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {

        }
    }

    @FXML
    private void addPartSave(ActionEvent actionEvent) {
        if (radioInternal.isSelected()) {
            addInternal();
        }
        if (radioExternal.isSelected()) {
            addExternal();
        }
    }

    private void addInternal() {
        currentInventory.addPart(new InHouse(Integer.parseInt(NewIDField.getText()),NewNameField.getText(),
                Integer.parseInt(NewPriceField.getText()), Integer.parseInt(NewStockField.getText()),
                Integer.parseInt(NewMinField.getText()), Integer.parseInt(NewMaxField.getText()),
                Integer.parseInt(NewFlexField.getText())));
    }

    private void addExternal() {
        currentInventory.addPart(new Outsourced(Integer.parseInt(NewIDField.getText()),NewNameField.getText(),
                Integer.parseInt(NewPriceField.getText()), Integer.parseInt(NewStockField.getText()),
                Integer.parseInt(NewMinField.getText()), Integer.parseInt(NewMaxField.getText()),
                NewFlexField.getText()));
    }
}
