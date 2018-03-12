package controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import javafx.stage.Stage;

public class Main extends Application {



    @FXML
    private Button submitButton;

    @FXML
    private MenuItem generateData;

    @FXML
    void handleSubmitButtonClick()
    {

        System.out.println("Submit Button clicked ! ");
    }

    @FXML
    void handleGenerateDataMenuItem()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/generateData.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage=new Stage();
            stage.setTitle("Generate Data");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e)
        {
            System.out.println("GenerateDataMenuItem Exception");
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/MainScene.fxml"));
        primaryStage.setTitle("Salesman");

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
