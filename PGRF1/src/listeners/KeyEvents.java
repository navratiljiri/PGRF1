package listeners;

import constants.Colors;
import controller.Controller2D;
import enums.Algorithms;
import model.Line;
import view.Help;
import view.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEvents implements KeyListener {
    Panel panel;
    Help helpPanel;
    Controller2D controller2D;
    int bold;

    boolean shiftActivated = false;

    public KeyEvents(Panel panel, Help helpPanel, Controller2D controller2D) {
        this.panel = panel;
        this.helpPanel = helpPanel;
        this.controller2D = controller2D;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_C:
                clearAll();
                panel.repaint();
                break;
            case KeyEvent.VK_UP:
                panel.clear(Colors.BLACK);
                bold = controller2D.getLineRasterizer().getBold();
                controller2D.getLineRasterizer().setBold(++bold);
                makeBoldLine();
                break;
            case KeyEvent.VK_DOWN:
                panel.clear(Colors.BLACK);
                bold = controller2D.getLineRasterizer().getBold();
                if(bold > 0) {
                    controller2D.getLineRasterizer().setBold(--bold);
                }
                makeBoldLine();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_SHIFT:
                shiftActivated = true;
                break;
            case KeyEvent.VK_A:
                int value = helpPanel.getSelectedAlgoritm();

                if (value == Algorithms.values().length - 1) {
                    helpPanel.setSelectedAlgoritm(0);
                }
                else {
                    helpPanel.setSelectedAlgoritm(++value);
                }
                helpPanel.repaint();
                controller2D.changeAlgorithms();
                break;
            case KeyEvent.VK_R:
                if(controller2D.getPolygon().getSize() > 0) {
                    controller2D.getPolygon().removePoint(controller2D.getPolygon().findNearestPoint(panel.getRasterImage().getX(), panel.getRasterImage().getY()));
                    panel.clear(Colors.BLACK);
                    if(controller2D.getPolygon().getSize() == 1) {
                        clearAll();
                    }
                    else {
                        makeBoldLine();
                    }
                    panel.repaint();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftActivated = false;
        }
    }

    public void makeBoldLine() {
        if(controller2D.getPolygon().getSize() > 2) {
            controller2D.getPolygonRasterizer().rasterize(controller2D.getPolygon(), controller2D.getLineRasterizer().getBold());
        }
        else {
            int x1 = controller2D.getPolygon().getPoint(0).getX();
            int x2 = controller2D.getPolygon().getPoint(controller2D.getPolygon().getSize() -1).getX();
            int y1 = controller2D.getPolygon().getPoint(0).getY();
            int y2 = controller2D.getPolygon().getPoint(controller2D.getPolygon().getSize() -1).getY();
            controller2D.getLineRasterizer().rasterize(new Line(x1,y1,x2,y2, Colors.WHITE), controller2D.getLineRasterizer().getBold());
        }
        panel.repaint();
    }

    public boolean isShiftActivated() {
        return shiftActivated;
    }
    public void clearAll() {
        controller2D.getPolygon().clearPolygon();
        panel.clear(Colors.BLACK);
    }
}
