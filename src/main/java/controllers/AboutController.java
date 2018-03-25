package controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AboutController{

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/about.fxml"));
        primaryStage.setTitle("About Application");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
