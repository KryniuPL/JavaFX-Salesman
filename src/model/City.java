package model;

import javafx.geometry.Point2D;

public class City extends Point2D implements Comparable<City> {

    private final Point2D location;

    public City(double x, double y)
    {
        super(x, y);
        this.location = new Point2D(x, y);
    }

    @Override
    public int compareTo(City o) {
        return (int) this.getLocation().distance(o.getLocation());
    }

    public int distanceTo(City o)
    {
        return (int) this.getLocation().distance(o.getLocation());
    }

    public Point2D getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return location.toString();
    }
}
