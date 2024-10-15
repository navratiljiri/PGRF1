package listeners;

import model.Line;
import model.Point;
import model.Polygon;
import rasterizer.LineRasterizer;
import rasterizer.PolygonRasterizer;
import view.Panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMotionListener extends MouseAdapter {
    Panel panel;
    LineRasterizer lineRasterizer;
    PolygonRasterizer polygonRasterizer;
    Polygon polygon;
    int x, y, x2, y2;

    public MouseMotionListener(Panel panel, LineRasterizer lineRasterizer, PolygonRasterizer polygonRasterizer, Polygon polygon) {
        this.panel = panel;
        this.lineRasterizer = lineRasterizer;
        this.polygonRasterizer = polygonRasterizer;
        this.polygon = polygon;
    }

    public void changeAlgorithm(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        polygon.addPoint(new Point(x, y));
        panel.getRasterImage().createImagePrediction();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        panel.clear(0x000000);
        if (polygon.getSize() < 3) {
            lineRasterizer.rasterize(new Line(x, y, x2, y2, 0xffffff));
        }
        polygon.addPoint(new Point(x2, y2));
        polygonRasterizer.rasterize(polygon);
        panel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();

        panel.getRasterImage().setImagePredictionToImage();

        if (polygon.getSize() < 3) {
            lineRasterizer.rasterize(new Line(x, y, x2, y2, 0xff0000));
        } else {
            Point lastPoint = polygon.getPoint(polygon.getSize()-2);
            lineRasterizer.rasterize(new Line(polygon.getPoint(0).getX(), polygon.getPoint(0).getY(), x2, y2, 0xff0000));
            lineRasterizer.rasterize(new Line(lastPoint.getX(), lastPoint.getY(), x2, y2, 0xff0000));
        }

        panel.getRasterImage().setCoordinator(x2, y2);
        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        panel.getRasterImage().setCoordinator(e.getX(), e.getY());
        panel.repaint();
    }
}
