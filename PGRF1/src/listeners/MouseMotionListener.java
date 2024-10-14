package listeners;

import model.Line;
import rasterizer.LineRasterizer;
import view.Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMotionListener extends MouseAdapter {
    Panel panel;
    LineRasterizer lineRasterizer;
    int x,y, x2, y2;
    int panelHeight;
    int panelWidth;

    public MouseMotionListener(Panel panel, LineRasterizer lineRasterizer) {
        this.panel = panel;
        this.lineRasterizer = lineRasterizer;
        panelWidth = panel.getRasterImage().getWidth() - 2;
        panelHeight = panel.getRasterImage().getHeight() - 2;
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
        panel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        panel.getRasterImage().setImagePredictionToImage();

        lineRasterizer.rasterize(new Line(x,y,x2,y2, 0xff00000));
        panel.getRasterImage().setCoordinator(x2, y2);
        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        panel.getRasterImage().setCoordinator(e.getX(), e.getY());
        panel.repaint();
    }
}
