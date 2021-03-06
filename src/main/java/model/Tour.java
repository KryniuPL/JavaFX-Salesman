
package model;

import java.util.ArrayList;
import java.util.Collections;


public class Tour implements Comparable<Tour> {

    private ArrayList<City> tour = new ArrayList<>();
    private double fitness = 0;
    private int distance = 0;

    // Constructs a blank tour
    public Tour() {
        for (int i = 0; i < CityManager.getInstance().numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public Tour(ArrayList tour) {
        this.tour = tour;
    }

    // Shuffling list
    public void generateIndividual() {
        for (int cityIndex = 0; cityIndex < CityManager.getInstance().numberOfCities(); cityIndex++) {
            setCity(cityIndex, CityManager.getInstance().getCity(cityIndex));
        }
        Collections.shuffle(tour);
    }

    public City getCity(int tourPosition) {
        return tour.get(tourPosition);
    }

    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        fitness = 0;
        distance = 0;
    }

    // Gets the tours fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / (double) getDistance();
        }
        return fitness;
    }

    // Gets the total distance of the tour
    public int getDistance() {
        if (distance == 0) {
            int tourDistance = 0;
            for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
                City fromCity = getCity(cityIndex);
                City destinationCity;
                if (cityIndex + 1 < tourSize()) {
                    destinationCity = getCity(cityIndex + 1);
                } else {
                    destinationCity = getCity(0);
                }
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }

    // Check if the tour contains a city
    public boolean containsCity(City city) {
        return tour.contains(city);
    }

    @Override
    public int compareTo(Tour o) {
        if (o == null) {
            return -1;
        }
        return (int) (this.distance - o.distance);
    }
}
