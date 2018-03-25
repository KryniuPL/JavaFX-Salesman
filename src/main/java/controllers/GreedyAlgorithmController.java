package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GreedyAlgorithmController {

    @FXML
    private Button returnButton;

    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../views/greedyAlgorithm.fxml"));
        primaryStage.setTitle("GreedyAlgorithm");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    public void handleReturnToMenuButton()throws Exception {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
        MainController mainController=new MainController();
        mainController.start(new Stage());
    }
}
