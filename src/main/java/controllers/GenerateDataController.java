package controllers;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.City;

import java.io.*;

import java.util.Random;

public class GenerateDataController {

    @FXML
    public TextField NumberOfPoints;

    public static ObservableList<City> points = FXCollections.observableArrayList();
    private final double rangeMinX = 20;
    private final double rangeMaxX = 980;
    private final double rangeMinY = 20;
    private final double rangeMaxY = 530;

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/generateData.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Generate Data");
        stage.setScene(new Scene(root));
        stage.show();
    }

    void drawData() {
        int IntegerPoints = Integer.parseInt(NumberOfPoints.getText());
        double randomValueX;
        double randomValueY;

        for (int i = 0; i < IntegerPoints; i++) {
            if (i == 0) {
                Random random = new Random();
                randomValueX = rangeMinX + (rangeMaxX - rangeMinX) * random.nextDouble();
                randomValueY = rangeMinY + (rangeMaxY - rangeMinY) * random.nextDouble();
                City city = new City(randomValueX, randomValueY);
                points.add(city);
            } else {
                Random random = new Random();
                randomValueX = rangeMinX + (rangeMaxX - rangeMinX) * random.nextDouble(); //current X
                randomValueY = rangeMinY + (rangeMaxY - rangeMinY) * random.nextDouble(); //current Y

                for(int tmp=points.size()-1;tmp>=0;tmp--) {
                    double getPreviousX = points.get(tmp).getX();//X from previous City Object
                    double getPreviousY = points.get(tmp).getY();//Y from previous City Object

                    while (Math.abs(randomValueX - getPreviousX) < 11) {
                        randomValueX = rangeMinX + (rangeMaxX - rangeMinX) * random.nextDouble();
                    }
                    while (Math.abs(randomValueY - getPreviousY) < 11) {
                        randomValueY = rangeMinY + (rangeMaxY - rangeMinY) * random.nextDouble();
                    }
                }

                City city = new City(randomValueX, randomValueY);
                points.add(city);
            }
        }
    }

    void saveDataToFile() throws FileNotFoundException {
        PrintWriter printWriter=new PrintWriter("points.txt");
        printWriter.print("");
        printWriter.close();
        try {
            FileWriter writer = new FileWriter("points.txt");
            for (City point : points) {
                writer.write(String.valueOf(point.getX()));
                writer.write(System.getProperty("line.separator"));
                writer.write(String.valueOf(point.getY()));
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Fix me!!! - Reports Java Writer");
            e.printStackTrace();
        }
        points.clear();
    }

    @FXML
    void generateData() throws FileNotFoundException {
        drawData();
        saveDataToFile();
        Stage stage = (Stage) NumberOfPoints.getScene().getWindow();
        stage.close();
    }


}
