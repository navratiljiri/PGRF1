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
    KeyEvents keyEvents;
    private int x, y, x2, y2;
    private double k;

    public MouseMotionListener(Panel panel, LineRasterizer lineRasterizer, PolygonRasterizer polygonRasterizer, Polygon polygon, KeyEvents keyEvents) {
        this.panel = panel;
        this.lineRasterizer = lineRasterizer;
        this.polygonRasterizer = polygonRasterizer;
        this.polygon = polygon;
        this.keyEvents = keyEvents;
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
            checkShift(0xffffff);
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
            checkShift(0xff0000);
        } else {
            Point lastPoint = polygon.getPoint(polygon.getSize() - 2);
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

    /**
     * Zkontroluje zdali je stisknutý shift a podle toho vygeneruje úsečku
     *
     * @param color
     */
    private void checkShift(int color) {
        if (!keyEvents.isShiftActivated()) {
            lineRasterizer.rasterize(new Line(x, y, x2, y2, color));
            return;
        }
        int dy = y2 - y;
        int dx = x2 - x;
        if (dx == 0) {
            dx = 1;
        }
        k = dy / dx;

        //horizontála
        if (Math.abs(k) < 1) {
            lineRasterizer.rasterize(new Line(x, y, x2, y, color));
            y2 = y;
        }
        //osa x a y je stejná --> uhlopříčka pod úhlem 45
        else if (Math.abs(k) == 1) {
            int delta = Math.min(Math.abs(dx), Math.abs(dy)); //nejmenší rozdíl mezi souřadnicemi
            //Určení směru
            int signX = (x2 > x) ? 1 : -1;
            int signY = (y2 > y) ? 1 : -1;
            lineRasterizer.rasterize(new Line(x, y, x + signX * delta, y + signY * delta, color));
            y2 = y + signY * delta;
            x2 = x + signX * delta;
        }
        //svislá
        else {
            lineRasterizer.rasterize(new Line(x, y, x, y2, color));
            x2 = x;
        }

    }
}
