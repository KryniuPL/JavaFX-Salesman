package controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.stage.Stage;



public class MainController extends Application {



    @FXML
    private Button submitButton;

    @FXML
    private ChoiceBox<String> ChoiceBox;

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../views/MainScene.fxml"));
        primaryStage.setTitle("Salesman");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

    }
    @FXML
    void handleSubmitButtonClick() throws Exception{
        String selected=ChoiceBox.getSelectionModel().getSelectedItem();
        if(selected.equals("Genetic Algorithm"))
        {
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
            GeneticAlgorithmController geneticAlgorithmController = new GeneticAlgorithmController();
            geneticAlgorithmController.start(new Stage());
        }
        else if (selected.equals("Nearest Neighbour Algorithm"))
        {
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
            NearestNeighbourAlgorithmController nearestNeighbourAlgorithmController=new NearestNeighbourAlgorithmController();
            nearestNeighbourAlgorithmController.start(new Stage());
        }
        else {
            Stage stage=(Stage) submitButton.getScene().getWindow();
            stage.close();
            GreedyAlgorithmController greedyAlgorithmController=new GreedyAlgorithmController();
            greedyAlgorithmController.start(new Stage());
        }
    }

    @FXML
    void handleAbout() throws Exception {
        AboutController aboutController=new AboutController();
        aboutController.start(new Stage());
    }

    @FXML
    void handleGenerateDataMenuItem() throws Exception {
        GenerateDataController generateDataController = new GenerateDataController();
        generateDataController.start(new Stage());
    }

    @FXML
    public void handleShowGeneratedData() throws Exception {
        ShowPointsController showPointsController = new ShowPointsController();
        showPointsController.start(new Stage());
    }


}


