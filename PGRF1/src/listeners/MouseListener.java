package listeners;

import model.Line;
import rasterizer.LineRasterizer;
import view.Panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    LineRasterizer lineRasterizer;
    Panel panel;
    int x1, y1, x2, y2;

    public MouseListener(LineRasterizer lineRasterizer, Panel panel) {
        this.lineRasterizer = lineRasterizer;
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        lineRasterizer.rasterize(new Line(x1,y1,x2,y2, 0xffffff));
        panel.repaint();
    }

}
