package controller;

import rasterizer.LineRasterizer;
import rasterizer.LineRasterizerGraphics;
import rasterizer.LineRasterizerTrivial;
import rasterizer.Raster;
import view.Panel;

public class Controller2D {
    private final Panel panel;
    private LineRasterizer lineRasterizer;

    public Controller2D(Panel panel) {
        this.panel = panel;

        initObjects(panel.getRasterImage());
        //init listeners
    }

    public void initObjects(Raster raster) {
        lineRasterizer = new LineRasterizerTrivial(raster);
    }
}
