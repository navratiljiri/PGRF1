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

    public void removePoint(Point p) {
        this.points.remove(p);
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

    public void changePoint(int index, Point p) {
        this.points.set(index, p);
    }

    public int getIndexOfPoint(Point p) {
        return points.indexOf(p);
    }

    /**
     * Euklidův algoritmus pro nalezení nejbližšího bodu
     * @param x
     * @param y
     * @return nejbližší bod
     */
    public Point findNearestPoint(int x, int y) {
        Point nearestPoint = null;
        double nearestDistance = -1;
        double d;
        for (Point p : points) {
             d = Math.sqrt(Math.pow((x - p.getX()),2)+Math.pow((y - p.getY()),2));
             if(d < nearestDistance || nearestDistance == -1) {
                 nearestDistance = d;
                 nearestPoint = p;
             }
        }
        return nearestPoint;
    }

}
