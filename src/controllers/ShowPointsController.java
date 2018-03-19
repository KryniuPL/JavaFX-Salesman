package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShowPointsController{


    TableView<Point> table;

    public ObservableList<Point> getData() throws Exception {
        ObservableList<Double> xPoints = getXValues();
        ObservableList<Double> yPoints = getYValues();
        ObservableList<Point> points = FXCollections.observableArrayList();
        for (int i = 0; i < xPoints.size(); i++)
        {
            points.add(new Point(xPoints.get(i), yPoints.get(i)));
        }
        return points;
    }


    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Points");
        primaryStage.setHeight(400);
        primaryStage.setHeight(650);

        TableColumn<Point, String> xColumn = new TableColumn<>("X Values");
        xColumn.setMinWidth(200);
        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));

        TableColumn<Point, String> yColumn = new TableColumn<>("Y Values");
        yColumn.setMinWidth(200);
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        table = new TableView<>();
        table.setPrefHeight(650);
        table.setItems(getData());
        table.getColumns().addAll(xColumn, yColumn);


        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static ObservableList<Double> getPointsFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("points.txt"));
        ObservableList<Double> pointsFromFile = FXCollections.observableArrayList();
        while (scanner.hasNext()) {
            pointsFromFile.add(Double.valueOf(scanner.next()));
        }
        scanner.close();
        return pointsFromFile;
    }

    public static ObservableList<Double> getXValues() throws FileNotFoundException {
        ObservableList<Double> tmp = ShowPointsController.getPointsFromFile();
        ObservableList<Double> xPoints = FXCollections.observableArrayList();

        for (int i = 0; i < tmp.size(); i = i + 2) {
            Double tmp2 = tmp.get(i);
            xPoints.add(tmp2);
        }
        return xPoints;
    }

    public static ObservableList<Double> getYValues() throws FileNotFoundException {
        ObservableList<Double> tmp = ShowPointsController.getPointsFromFile();
        ObservableList<Double> yPoints = FXCollections.observableArrayList();
        for (int i = 1; i < tmp.size(); i = i + 2) {
            Double tmp2 = tmp.get(i);
            yPoints.add(tmp2);
        }
        return yPoints;
    }


}
