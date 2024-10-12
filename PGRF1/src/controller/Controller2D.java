package controller;

import listeners.MouseListener;
import listeners.MouseMotionListener;
import model.Line;
import rasterizer.*;
import view.Panel;

public class Controller2D {
    private final Panel panel;
    private LineRasterizer lineRasterizer;
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;

    public Controller2D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRasterImage());
        initListeners();
    }

    public void initObjects(Raster raster) {
        lineRasterizer = new LineRasterizerDDA(raster);
    }
    public void initListeners() {
        /*mouseListener = new MouseListener(lineRasterizer, panel);
        panel.addMouseListener(mouseListener);*/

        mouseMotionListener = new MouseMotionListener(panel, lineRasterizer);
        panel.addMouseListener(mouseMotionListener);
        panel.addMouseMotionListener(mouseMotionListener);
    }
}
