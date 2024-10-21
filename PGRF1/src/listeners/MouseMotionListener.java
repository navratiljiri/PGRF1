package listeners;

import constants.Colors;
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
    private boolean editMode = false;
    private Point nearestPoint;
    int index, indexBefore, indexAfter = -1;

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
        panel.getRasterImage().createImagePrediction();
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (polygon.getSize() == 0) {
                polygon.addPoint(new Point(x, y));
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            editMode = true;
            nearestPoint = polygon.findNearestPoint(x, y);

            indexBefore = polygon.getIndexOfPoint(nearestPoint) - 1;
            index = polygon.getIndexOfPoint(nearestPoint);
            if (indexBefore < 0) {
                indexBefore = polygon.getSize() - 1;
            }
            indexAfter = polygon.getIndexOfPoint(nearestPoint) + 1;
            if (indexAfter < 0) {
                indexAfter = polygon.getSize() - 1;
            } else if (indexAfter > polygon.getSize() - 1) {
                indexAfter = 0;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        panel.clear(Colors.BLACK);
        if (!editMode) {
            if (polygon.getSize() < 2) {
                checkShift(x, y, Colors.WHITE);
            }
            polygon.addPoint(new Point(x2, y2));
        } else {
            if (polygon.getSize() <= 2 && polygon.getSize() > 1) {
                checkShift(polygon.getPoint(indexBefore).getX(), polygon.getPoint(indexAfter).getY(), Colors.WHITE);
            }
            editMode = false;
        }
        polygonRasterizer.rasterize(polygon, lineRasterizer.getBold());
        panel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        panel.getRasterImage().setImagePredictionToImage();
        if (!editMode) {
            if (polygon.getSize() < 2) {
                checkShift(x, y, Colors.RED);
            } else {
                Point lastPoint = polygon.getPoint(polygon.getSize() - 1);
                checkShift(lastPoint.getX(), lastPoint.getY(), Colors.RED);
                lineRasterizer.rasterize(new Line(polygon.getPoint(0).getX(), polygon.getPoint(0).getY(), x2, y2, Colors.RED), lineRasterizer.getBold());
            }
        } else if(polygon.getSize() > 1) {
            if (indexAfter != indexBefore) {
                checkShift(polygon.getPoint(indexBefore).getX(), polygon.getPoint(indexBefore).getY(), Colors.RED);
                lineRasterizer.rasterize(new Line(polygon.getPoint(indexAfter).getX(), polygon.getPoint(indexAfter).getY(), x2, y2, Colors.RED), lineRasterizer.getBold());
            } else {
                checkShift(polygon.getPoint(indexBefore).getX(), polygon.getPoint(indexBefore).getY(), Colors.RED);
            }
            polygon.changePoint(index, new Point(x2, y2));
        }
        panel.getRasterImage().setCoordinator(e.getX(), e.getY());
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
    private void checkShift(int x, int y, int color) {
        if (!keyEvents.isShiftActivated()) {
            lineRasterizer.rasterize(new Line(x, y, x2, y2, color),  lineRasterizer.getBold());
            return;
        }

        int dy = y2 - y;
        int dx = x2 - x;
        if (dx == 0) {
            dx = 1;
        }

        k = dy / dx;

        /**
         * Horizontála
         */
        if (Math.abs(k) < 1) {
            lineRasterizer.rasterize(new Line(x, y, x2, y, color), lineRasterizer.getBold());
            y2 = y;
        }
        /**
         * Svislá
         */
        else if (Math.abs(k) == 1) {  //osa x a y je stejná --> uhlopříčka pod úhlem 45
            int delta = Math.min(Math.abs(dx), Math.abs(dy)); //nejmenší rozdíl mezi souřadnicemi
            //Určení kvadrantu
            int signX = (x2 > x) ? 1 : -1;
            int signY = (y2 > y) ? 1 : -1;
            lineRasterizer.rasterize(new Line(x, y, x + signX * delta, y + signY * delta, color),  lineRasterizer.getBold());
            y2 = y + signY * delta;
            x2 = x + signX * delta;
        }
        /**
         * Vertikála
         */
        else {
            lineRasterizer.rasterize(new Line(x, y, x, y2, color),  lineRasterizer.getBold());
            x2 = x;
        }
    }
}
