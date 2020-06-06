package Controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

public class alertMessages {
    public static void errorCount(int code, TextField field) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        if (code == 1) {
            alert.setHeaderText("Invalid Count");
            alert.setContentText("Count cannot be outside of min/max boundaries.");
        }
        if (code == 2) {
            alert.setHeaderText("Internal Part Machine ID Value Error");
            alert.setContentText("Machine ID must be an integer.");
        }
        if (code == 3) {
            alert.setHeaderText("Name is Required");
            alert.setContentText("Please be sure to enter a name!");
        }
        if (code == 4) {
            alert.setHeaderText("Profit Warning");
            alert.setContentText("Sale price cannot be less than the sum of components!");
        }
        if (code == 5) {
            alert.setHeaderText("Duplicate Part Warning");
            alert.setContentText("Duplicate parts are not valid");
        }
        if (code == 6) {
            alert.setHeaderText("Insufficient Parts");
            alert.setContentText("Each product must have at least one part.");
        }
        if (code == 7) {
            alert.setHeaderText("Insufficient Price");
            alert.setContentText("Price cannot be equal to or less than 0.");
        }
        alert.showAndWait();
    }

    public static boolean confirmationWindow(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm action");
        alert.setHeaderText("This action will remove data.");
        alert.setContentText("Click ok to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
