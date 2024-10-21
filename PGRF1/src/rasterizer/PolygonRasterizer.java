package rasterizer;

import model.Line;
import model.Point;
import model.Polygon;

public class PolygonRasterizer {
    private LineRasterizer lineRasterizer;

    public PolygonRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    public void rasterize(Polygon polygon, int bold) {
        if (polygon.getSize() < 3) {
            return;
        }
        Point pointA, pointB;
        for (int i = 0; i < polygon.getSize(); i++) {
            pointA = polygon.getPoint(i);
            if (i == polygon.getSize() - 1) {
                pointB = polygon.getPoint(0);
            } else {
                pointB = polygon.getPoint(i + 1);
            }
            lineRasterizer.rasterize(new Line(pointA, pointB, 0xffffff), bold);
        }
    }

    public void setLineRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }
}
