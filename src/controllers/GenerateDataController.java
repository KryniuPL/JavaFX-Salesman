package controllers;




import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateDataController {

    @FXML
    public TextField NumberOfPoints;

    private List<Double> points;
    private double rangeMin=0;
    private double rangeMax=100;

    @FXML
    void generateData()
    {
        int IntegerPoints=Integer.parseInt(NumberOfPoints.getText());
        double randomValueX=0;
        double randomValueY=0;

        System.out.println(IntegerPoints);
        points=new ArrayList<>();


        for (int i=0;i<IntegerPoints;i++) {
            Random random=new Random();
            randomValueX=rangeMin+(rangeMax-rangeMin)*random.nextDouble();
            randomValueY=rangeMin+(rangeMax-rangeMin)*random.nextDouble();
            points.add(randomValueX);
            points.add(randomValueY);
        }
        try {
            FileWriter writer=new FileWriter("points.txt");
            for (Double point: points)
            {
                writer.write(point.toString());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("POPSUTE!!!");
            e.printStackTrace();
        }

        //TODO
    }
}
