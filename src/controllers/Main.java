package controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class Main extends Application {

    @FXML
    private MenuItem close;
    private Stage tmpStage=new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/MainScene.fxml"));
        primaryStage.setTitle("Salesman");
        primaryStage.setScene(new Scene(root, 600, 400));
        tmpStage=primaryStage;
        primaryStage.show();
    }

    @FXML
    void handleCloseWindows()
    {

        tmpStage.close();
    }

    @FXML
    void handleSubmitButtonClick() {
        //Główne liczenie
        //TODO
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


