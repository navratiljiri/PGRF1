package model;

import java.util.ArrayList;

public class Polygon {
    private ArrayList<Point> points;
    public Polygon() {
        this.points = new ArrayList<>();
    }
    public void clearPolygon() {
        this.points.clear();
    }
    public void addPoint(Point p) {
        this.points.add(p);
    }
    public Point getPoint(int index) {
        return points.get(index);
    }
    public int getSize() {
        return points.size();
    }
}
