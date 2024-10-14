package controller;

import listeners.KeyEvents;
import listeners.MouseMotionListener;
import rasterizer.*;
import view.Help;
import view.Panel;

public class Controller2D {
    private final Panel panel;
    private final Help helpPanel;
    private LineRasterizer lineRasterizer;
    private KeyEvents keyEvents;
    private MouseMotionListener mouseMotionListener;
    private Raster raster;

    public Controller2D(Panel panel, Help helpPanel) {
        this.panel = panel;
        this.helpPanel = helpPanel;
        initObjects(panel.getRasterImage());
        initListeners();
    }

    public void initObjects(Raster raster) {
        this.raster = raster;
        lineRasterizer = new LineRasterizerTrivial(raster);
    }

    public void changeAlgorithms() {
        switch (helpPanel.getSelectedAlgoritm()) {
            case 0:
                lineRasterizer = new LineRasterizerTrivial(raster);
                break;
            case 1:
                lineRasterizer = new LineRasterizerDDA(raster);
                break;
        }
        mouseMotionListener.changeAlgorithm(lineRasterizer);
    }

    public void initListeners() {
        mouseMotionListener = new MouseMotionListener(panel, lineRasterizer);
        keyEvents = new KeyEvents(panel, helpPanel, this);

        panel.addKeyListener(keyEvents);
        panel.addMouseListener(mouseMotionListener);
        panel.addMouseMotionListener(mouseMotionListener);

    }
}
