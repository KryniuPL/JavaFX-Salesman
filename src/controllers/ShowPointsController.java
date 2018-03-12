package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShowPointsController{


    public static ObservableList<Double> getPointsFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("points.txt"));
        ObservableList<Double> pointsFromFile= FXCollections.observableArrayList();
        while (scanner.hasNext())
        {
            pointsFromFile.add(Double.valueOf(scanner.next()));
        }
        scanner.close();
        return pointsFromFile;
    }

    public static ObservableList<Double> getXValues() throws FileNotFoundException
    {
        ObservableList<Double> tmp= ShowPointsController.getPointsFromFile();
        ObservableList<Double> xPoints= FXCollections.observableArrayList();


        for (int i=0;i<tmp.size();i=i+2)
        {
            Double tmp2=tmp.get(i);
            xPoints.add(tmp2);
        }
        System.out.println(xPoints.size());
        return xPoints;
    }

    public static ObservableList<Double> getYValues() throws FileNotFoundException
    {
        ObservableList<Double> tmp= ShowPointsController.getPointsFromFile();
        ObservableList<Double> yPoints= FXCollections.observableArrayList();


        for (int i=1;i<tmp.size();i=i+2)
        {
            Double tmp2=tmp.get(i);
            yPoints.add(tmp2);
        }
        System.out.println(yPoints.size());
        return yPoints;
    }


}
