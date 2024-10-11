package controller;

import model.Line;
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
        lineRasterizer.rasterize(new Line(100,50,10,0, 0x295396));
    }
}
