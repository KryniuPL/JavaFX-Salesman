package controllers;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class Main extends Application {

    @FXML
    TableView TableContent;

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

    @FXML
    public void handleShowGeneratedData()
    {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/ShowPoints.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage=new Stage();

            System.out.println(ShowPointsController.getPointsFromFile());
            System.out.println(ShowPointsController.getXValues());
            System.out.println(ShowPointsController.getYValues());

            TableContent=new TableView<>();
            TableContent.setItems(ShowPointsController.getXValues());

            stage.setTitle("Points");
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
