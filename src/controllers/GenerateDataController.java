package controllers;




import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.City;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateDataController {

    @FXML
    public TextField NumberOfPoints;

    public List<City> points;
    private double rangeMin=0;
    private double rangeMax=100;

    @FXML
    void generateData()
    {
        int IntegerPoints=Integer.parseInt(NumberOfPoints.getText());
        double randomValueX;
        double randomValueY;

        points=new ArrayList<>();


        for (int i=0;i<IntegerPoints;i++) {
            Random random=new Random();
            randomValueX=rangeMin+(rangeMax-rangeMin)*random.nextDouble();
            randomValueY=rangeMin+(rangeMax-rangeMin)*random.nextDouble();
            City city=new City(randomValueX,randomValueY);
            points.add(city);

        }
        try {
            FileWriter writer=new FileWriter("points.txt");
            for (City point: points)
            {
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

        Stage stage=(Stage) NumberOfPoints.getScene().getWindow();
        stage.close();





    }
}
