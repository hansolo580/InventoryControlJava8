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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Inventory currentInventory = new Inventory();
        loadSampleData(currentInventory);
        System.out.print("Sample data loaded.");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/mainView.fxml"));
        Controllers.mainController controller = new Controllers.mainController(currentInventory);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Parent root = FXMLLoader.load(getClass().getResource("/Views/mainView.fxml"));
        //primaryStage.setTitle("");
        //primaryStage.setScene(new Scene(root));
        //primaryStage.show();
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
