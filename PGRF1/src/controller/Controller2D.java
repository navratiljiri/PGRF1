package controller;

import listeners.KeyEvents;
import listeners.MouseMotionListener;
import rasterizer.*;
import view.Panel;

public class Controller2D {
    private final Panel panel;
    private LineRasterizer lineRasterizer;
    private KeyEvents keyEvents;
    private MouseMotionListener mouseMotionListener;

    public Controller2D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRasterImage());
        initListeners();
    }

    public void initObjects(Raster raster) {
        lineRasterizer = new LineRasterizerBresenham(raster);
    }
    public void initListeners() {
        mouseMotionListener = new MouseMotionListener(panel, lineRasterizer);
        keyEvents = new KeyEvents(panel);

        panel.addKeyListener(keyEvents);
        panel.addMouseListener(mouseMotionListener);
        panel.addMouseMotionListener(mouseMotionListener);

    }
}
