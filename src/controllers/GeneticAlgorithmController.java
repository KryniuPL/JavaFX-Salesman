package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.stage.Stage;

public class GeneticAlgorithmController {


    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../views/geneticAlgorithm.fxml"));
        primaryStage.setTitle("GeneticAlgorithm");
        primaryStage.show();
    }
}
