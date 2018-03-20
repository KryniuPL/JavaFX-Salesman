package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.*;

public class GeneticAlgorithmController {

    @FXML
    private Button returnButton;


    Tour bestTour;

    public void start(Stage primaryStage) throws Exception {
        BorderPane mainRoot = new BorderPane();
        Pane root = new Pane();

        Label label = new Label();
        label.setAlignment(Pos.BOTTOM_LEFT);
        label.setPadding(new Insets(0, 100, 15, 100));
        mainRoot.setCenter(root);
        mainRoot.setLeft(label);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        separator.setMinWidth(300);
        separator.setStyle("-fx-border-width: 5px");
        separator.setMinHeight(600);
        mainRoot.setLeft(separator);
        //separator.set
        //mainRoot.getChildren().add(separator);


        Scene scene = new Scene(mainRoot, 1000, 600);
        ShowPointsController showPointsController = new ShowPointsController();
        ObservableList<Point> pointsFromFile = showPointsController.getData();

        for (Point point : pointsFromFile) {
            Circle circle = new Circle(point.getX(), point.getY(), 10);
            System.out.println(circle.toString());
            CityManager.getInstance().addCity(new City(point.getX(), point.getY()));
            root.getChildren().add(circle);
        }

        bestTour = null;
        label.setText("Total City: " + CityManager.getInstance().numberOfCities());

        scene.setOnKeyTyped(event -> {
            // Initialize population

            Population pop = new Population(50, true);
            System.out.println("Initial distance: " + pop.getFittest().getDistance());

            // Evolve population for 100 generations
            pop = GeneticAlgorithm.evolvePopulation(pop);
            for (int i = 0; i < 100; i++) {
                pop = GeneticAlgorithm.evolvePopulation(pop);
            }

            Tour tour = pop.getFittest();
            if (tour.compareTo(bestTour) < 0) {
                bestTour = tour;
                label.setText("Total City: " + CityManager.getInstance().numberOfCities() + " | Current Best Distance: " + pop.getFittest().getDistance());
                root.getChildren().removeIf((Node t) -> {
                    return t.getClass().getSimpleName().equals("Line");
                });
                for (int i = 0; i < tour.tourSize() - 1; i++) {
                    Line line = new Line(tour.getCity(i).getLocation().getX(), tour.getCity(i).getLocation().getY(),
                            tour.getCity(i + 1).getLocation().getX(), tour.getCity(i + 1).getLocation().getY());
                    line.setStroke(Color.GREEN);
                    line.setStrokeWidth(5);

                    root.getChildren().add(line);
                    line.toBack();
                }
            }
        });
        primaryStage.setTitle("Genetic Algorithm");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void handleReturnToMenuButton() throws Exception {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
        MainController mainController = new MainController();
        mainController.start(new Stage());
    }
}
