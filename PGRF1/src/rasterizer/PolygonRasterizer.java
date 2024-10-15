package rasterizer;

import model.Line;
import model.Point;
import model.Polygon;

public class PolygonRasterizer {
    private LineRasterizer lineRasterizer;

    public PolygonRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    public void rasterize(Polygon polygon) {
        if (polygon.getSize() < 3) {
            return;
        }
        Point pointA, pointB;
        for (int i = 0; i < polygon.getSize(); i++) {
            pointA = polygon.getPoint(i);
            if (i + 1 == polygon.getSize()) {
                pointB = polygon.getPoint(0);
            } else {
                pointB = polygon.getPoint(i + 1);
            }
            lineRasterizer.rasterize(new Line(pointA, pointB, 0xffffff));
        }
    }

    public void setLineRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }
}
