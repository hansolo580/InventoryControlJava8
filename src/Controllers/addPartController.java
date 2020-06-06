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

import static Controllers.alertMessages.confirmationWindow;

public class addPartController implements Initializable {


    Inventory currentInventory;

    @FXML public ToggleGroup partSourceToggleGroup;
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

    @FXML public void cancel(ActionEvent event){
        boolean confirm = confirmationWindow("Cancel?");
        if (confirm) {
            changeScreenHome(event);
        }
    }

    @FXML public void changeScreenHome(ActionEvent event) {
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

    @FXML private void addPartSave(ActionEvent actionEvent) {
        if (Integer.parseInt(NewStockField.getText()) < Integer.parseInt(NewMinField.getText())) {
            alertMessages.errorCount(1, NewStockField);
        }
        else if (Integer.parseInt(NewStockField.getText()) > Integer.parseInt(NewMaxField.getText())) {
            alertMessages.errorCount(1, NewStockField);
        }
        else if (NewNameField.getText().isEmpty()) {
            alertMessages.errorCount(3, NewNameField);
        }
        else if (Double.parseDouble(NewPriceField.getText()) <= 0){
            alertMessages.errorCount(7, NewPriceField);
        }
        else if (radioInternal.isSelected()) {
            if (!NewFlexField.getText().trim().matches("[0-9]*")) {
                alertMessages.errorCount(2, NewFlexField);
            }
            addInternal();
            changeScreenHome(actionEvent);
        }
        else if (radioExternal.isSelected()) {
            addExternal();
            changeScreenHome(actionEvent);
        }

    }

    private void addInternal() {
        currentInventory.addPart(new InHouse(Integer.parseInt(NewIDField.getText()),NewNameField.getText(),
                Integer.parseInt(NewStockField.getText()), Double.parseDouble(NewPriceField.getText()),
                Integer.parseInt(NewMinField.getText()), Integer.parseInt(NewMaxField.getText()),
                Integer.parseInt(NewFlexField.getText())));
    }

    private void addExternal() {
        currentInventory.addPart(new Outsourced(Integer.parseInt(NewIDField.getText()),NewNameField.getText(),
                 Integer.parseInt(NewStockField.getText()), Double.parseDouble(NewPriceField.getText()),
                Integer.parseInt(NewMinField.getText()), Integer.parseInt(NewMaxField.getText()),
                NewFlexField.getText()));
    }
}
