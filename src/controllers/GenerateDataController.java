package controllers;



import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateDataController {

    @FXML
    public TextField NumberOfPoints;

    private List<Double> points;

    @FXML
    void generateData()
    {
        int IntegerPoints=Integer.parseInt(NumberOfPoints.getText());
        System.out.println(IntegerPoints);
        points=new ArrayList<>();



        points.add(2.21);
        points.add(22.32);

        try {
            FileWriter writer=new FileWriter("points.txt");
            for (Double point: points)
            {
                writer.write(point.toString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("POPSUTE!!!");
            e.printStackTrace();
        }

        //TODO
    }
}
