package Main;

import Models.Inventory;
import Models.Part;
import Models.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Models.InHouse;
import Models.Outsourced;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Inventory currentInventory = new Inventory();
        loadSampleData(currentInventory);
        System.out.print(currentInventory.lookupPart(1).getName());

        Parent root = FXMLLoader.load(getClass().getResource("/Views/mainView.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    void loadSampleData(Inventory newInventory) {
        //InHouse
        Part testPart1 = new InHouse(1, "Test Part 1", 5, 4.35, 1, 10, 349);
        newInventory.addPart(testPart1);
        //Outsource
        Part testPart2 = new Outsourced(2, "Test Part 2", 4, 2.38, 1, 10, "Weyland-Yutani");
        newInventory.addPart(testPart2);

        //Products
        Product testProduct1 = new Product(3, "Test Product 1", 15.34, 15, 1, 8);
        testProduct1.addAssociatedPart(testPart1);
        testProduct1.addAssociatedPart(testPart2);
        newInventory.addProduct(testProduct1);
    }
}
