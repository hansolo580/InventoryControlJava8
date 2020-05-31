package Controllers;

import Models.InHouse;
import Models.Inventory;
import Models.Outsourced;
import Models.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controllers.alertMessages.confirmationWindow;

public class modifyPartController implements Initializable {
    Inventory currentInventory;
    Part currentPart;

    @FXML private TextField modifyPartID;
    @FXML private TextField modifyPartName;
    @FXML private TextField modifyPartPrice;
    @FXML private TextField modifyPartStock;
    @FXML private TextField modifyPartMax;
    @FXML private TextField modifyPartFlex;
    @FXML private TextField modifyPartMin;
    @FXML private ToggleGroup partSourceToggleGroup;
    @FXML private Label partLabel;
    @FXML private RadioButton inHouseButton;
    @FXML private RadioButton outsourcedButton;

    public modifyPartController(Inventory currentInventory, Part currentPart) {
        this.currentInventory = currentInventory;
        this.currentPart = currentPart;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {parseData();}

    private void parseData() {
        if (currentPart instanceof InHouse) {
            InHouse newPart = (InHouse) currentPart;
            inHouseButton.setSelected(true);
            partLabel.setText("Machine ID");
            this.modifyPartName.setText(newPart.getName());
            this.modifyPartID.setText((Integer.toString(newPart.getID())));
            this.modifyPartStock.setText((Integer.toString(newPart.getStock())));
            this.modifyPartPrice.setText((Double.toString(newPart.getPrice())));
            this.modifyPartMin.setText((Integer.toString(newPart.getMin())));
            this.modifyPartMax.setText((Integer.toString(newPart.getMax())));
            this.modifyPartFlex.setText((Integer.toString(newPart.getMachineID())));
        }
        else {
            Outsourced newPart2 = (Outsourced) currentPart;
            outsourcedButton.setSelected(true);
            partLabel.setText("Company Name");
            this.modifyPartName.setText(newPart2.getName());
            this.modifyPartID.setText((Integer.toString(newPart2.getID())));
            this.modifyPartStock.setText((Integer.toString(newPart2.getStock())));
            this.modifyPartPrice.setText((Double.toString(newPart2.getPrice())));
            this.modifyPartMin.setText((Integer.toString(newPart2.getMin())));
            this.modifyPartMax.setText((Integer.toString(newPart2.getMax())));
            this.modifyPartFlex.setText(newPart2.getCompanyName());
        }
    }

    @FXML private void selectedInternal(ActionEvent event) {
        partLabel.setText("Machine ID");
    }

    @FXML private void selectedExternal(ActionEvent event) {
        partLabel.setText("Company Name");
    }

    @FXML private void cancelGoHome(ActionEvent event) throws IOException {
        boolean confirm = confirmationWindow("Cancel?");
        if (confirm) {
            changeScreenHome(event);
        }
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

    @FXML public void modifyPartSave(ActionEvent actionEvent) throws IOException {
        if (Integer.parseInt(modifyPartStock.getText()) < Integer.parseInt(modifyPartMin.getText())) {
            alertMessages.errorCount(1, modifyPartStock);
        }
        if (Integer.parseInt(modifyPartStock.getText()) > Integer.parseInt(modifyPartMax.getText())) {
            alertMessages.errorCount(1, modifyPartStock);
        }
        if (inHouseButton.isSelected()) {
            modifyItemInternal();
        }
        if (outsourcedButton.isSelected()) {
            modifyItemExternal();
        }
        changeScreenHome(actionEvent);
    }

    private void modifyItemInternal() {
        currentInventory.updatePart(new InHouse(Integer.parseInt(modifyPartID.getText()), modifyPartName.getText(),
                Integer.parseInt(modifyPartStock.getText()), Double.parseDouble(modifyPartPrice.getText()),
                Integer.parseInt(modifyPartMin.getText()), Integer.parseInt(modifyPartMax.getText()),
                Integer.parseInt(modifyPartFlex.getText())));
    }

    private void modifyItemExternal() {
        currentInventory.updatePart(new Outsourced(Integer.parseInt(modifyPartID.getText()), modifyPartName.getText(),
                Integer.parseInt(modifyPartStock.getText()), Double.parseDouble(modifyPartPrice.getText()),
                Integer.parseInt(modifyPartMin.getText()), Integer.parseInt(modifyPartMax.getText()),
                modifyPartFlex.getText()));
    }
}
