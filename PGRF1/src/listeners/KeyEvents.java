package listeners;

import controller.Controller2D;
import enums.Algorithms;
import model.Polygon;
import rasterizer.PolygonRasterizer;
import view.Help;
import view.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEvents implements KeyListener {
    Panel panel;
    Help helpPanel;
    Controller2D controller2D;
    Polygon polygon;

    boolean shiftActivated = false;

    public KeyEvents(Panel panel, Help helpPanel, Controller2D controller2D, Polygon polygon) {
        this.panel = panel;
        this.helpPanel = helpPanel;
        this.controller2D = controller2D;
        this.polygon = polygon;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_C:
                polygon.clearPolygon();
                panel.clear(0x000000);
                panel.repaint();
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
                    helpPanel.repaint();
                    return;
                }
                helpPanel.setSelectedAlgoritm(++value);
                helpPanel.repaint();

                controller2D.changeAlgorithms();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                shiftActivated = false;
                break;
        }
    }

    public boolean isShiftActivated() {
        return shiftActivated;
    }
}
