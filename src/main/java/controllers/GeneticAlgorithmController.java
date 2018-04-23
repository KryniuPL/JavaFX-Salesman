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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;


public class GeneticAlgorithmController {


    AnchorPane mainRoot;

    Tour bestTour;


    private void addButton(Button button, AnchorPane anchorPane) {
        button.setLayoutX(14);
        button.setLayoutY(565);
        button.setPrefHeight(25);
        button.setPrefWidth(119);
        button.setText("Return to Menu");
        button.setMnemonicParsing(false);
        anchorPane.getChildren().add(button);
    }

    private void addSeparator(Separator separator, AnchorPane anchorPane) {
        separator.setLayoutY(549);
        separator.setPrefHeight(12);
        separator.setPrefWidth(1000);
        anchorPane.getChildren().add(separator);
    }

    private void addLabel(Label label, AnchorPane anchorPane) {
        label.setLayoutX(175);
        label.setLayoutY(565);
        label.setPrefHeight(17);
        label.setFont(new Font(16));
        label.setStyle("-fx-font: Bold");

        label.setPrefWidth(400);
        anchorPane.getChildren().add(label);
    }

    public void start(Stage primaryStage) throws Exception {
        mainRoot = new AnchorPane();
        Separator separator = new Separator();
        Button button = new Button();
        button.setOnAction(event -> {
            try {
                handleReturnToMenuButton(button);
                CityManager.getInstance().clearCities();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Label label = new Label();
        addSeparator(separator, mainRoot);
        addButton(button, mainRoot);
        addLabel(label, mainRoot);

        Scene scene = new Scene(mainRoot, 1000, 600);
        ShowPointsController showPointsController = new ShowPointsController();
        ObservableList<Point> pointsFromFile = showPointsController.getData();

        for (Point point : pointsFromFile) {
            Circle circle = new Circle(point.getX(), point.getY(), 10);
            CityManager.getInstance().addCity(new City(point.getX(), point.getY()));
            mainRoot.getChildren().add(circle);
        }

        bestTour = null;
        label.setText("Total City: " + CityManager.getInstance().numberOfCities());

        scene.setOnKeyTyped(event -> {

            // Initialize population
            Population pop = new Population((pointsFromFile.size()) / 2, true);

            // Evolve population for 100 generations
            pop = GeneticAlgorithm.evolvePopulation(pop);
            for (int i = 0; i < 1000; i++) {
                pop = GeneticAlgorithm.evolvePopulation(pop);
            }

            Tour tour = pop.getFittest();
            if (tour.compareTo(bestTour) < 0)
            {
                bestTour = tour;
                label.setText("Total City: " + CityManager.getInstance().numberOfCities() + " | Current Best Distance: " + pop.getFittest().getDistance());
                mainRoot.getChildren().removeIf((Node t) -> {
                    return t.getClass().getSimpleName().equals("Line");
                });

                for (int i = 0; i < tour.tourSize() - 1; i++) {
                    Line line = new Line(tour.getCity(i).getLocation().getX(), tour.getCity(i).getLocation().getY(),
                            tour.getCity(i + 1).getLocation().getX(), tour.getCity(i + 1).getLocation().getY());
                    line.setStroke(Color.GREEN);
                    line.setStrokeWidth(5);
                    mainRoot.getChildren().add(line);
                    line.toBack();
                }
            }
        });

        primaryStage.setTitle("Genetic Algorithm");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public void handleReturnToMenuButton(Button button) throws Exception {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        MainController mainController = new MainController();
        mainController.start(new Stage());
    }


}
