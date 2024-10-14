package listeners;

import model.Line;
import rasterizer.LineRasterizer;
import rasterizer.LineRasterizerGraphics;
import view.Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMotionListener extends MouseAdapter {
    Panel panel;
    LineRasterizer lineRasterizer;
    int x,y, x2, y2;

    public MouseMotionListener(Panel panel, LineRasterizer lineRasterizer) {
        this.panel = panel;
        this.lineRasterizer = lineRasterizer;
    }
    public void changeAlgorithm(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        panel.getRasterImage().createImagePrediction();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        lineRasterizer.rasterize(new Line(x,y,x2,y2, 0x5D2B68));
        /*int thickness = 6;
        for (int i = -thickness / 2; i <= thickness / 2; i++) {
            lineRasterizer.rasterize(new Line(x+i,y,x2+i,y2, 0x5D2B68));
            lineRasterizer.rasterize(new Line(x,y+i,x2,y2+i, 0x5D2B68));
        }*/
        panel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        panel.getRasterImage().setImagePredictionToImage();
        lineRasterizer.rasterize(new Line(x,y,x2,y2, 0x5D2B68));
        /*int thickness = 6;
        for (int i = -thickness / 2; i <= thickness / 2; i++) {
            lineRasterizer.rasterize(new Line(x+i,y,x2+i,y2, 0x5D2B68));
            lineRasterizer.rasterize(new Line(x,y+i,x2,y2+i, 0x5D2B68));
        }*/
        panel.getRasterImage().setCoordinator(x2, y2);
        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        panel.getRasterImage().setCoordinator(e.getX(), e.getY());
        panel.repaint();
    }
}
