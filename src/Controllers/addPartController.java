package Controllers;

import Models.Inventory;
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
import java.util.ResourceBundle;

public class addPartController implements Initializable {

    public ToggleGroup partSourceToggleGroup;
    Inventory currentInventory;

    @FXML private Label partLabel;
    @FXML
    void AddPartButtonInternal(ActionEvent event) {
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

    public void addPartController(Inventory currentInventory) {
        this.currentInventory = currentInventory;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  setFields();
    }

    //private void setFields() {
     //   id.setText("Part ID");
      //  name.setText("Part Name");
      //  count.setText("Current Inventory");
      //  price.setText("Price");
      //  min.setText("Min");
     //   max.setText("Max");
      //  company.setText("Company");
     //   partLabel.setText("Company");
      //  radioExternal.setSelected(true);
   // }

    public void changeScreenHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/mainView.fxml"));
        mainController controller = new mainController(currentInventory);

        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        //Parent homeParent = FXMLLoader.load(getClass().getResource("/Views/mainView.fxml"));
        //Scene homeScene = new Scene(homeParent);

        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        //window.setScene(homeScene);
        //window.show();
    }

    public void addPartSave(ActionEvent actionEvent) {
    }
}
