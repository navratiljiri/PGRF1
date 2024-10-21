package rasterizer;

import model.Line;

public abstract class LineRasterizer {
    Raster raster;
    int color;
    int bold;

    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void rasterize(Line line, int bold) {
        setColor(line.getColor());
        if(bold == 0) {
            drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
            return;
        }
        for (int i = 0; i < bold; i++) {
            drawLine(line.getX1() +i, line.getY1(), line.getX2()+i, line.getY2());
            drawLine(line.getX1(), line.getY1()+i, line.getX2(), line.getY2() + i);
        }
    }

    protected void drawLine(int x1, int y1, int x2, int y2) {

    }

    public int getBold() {
        return bold;
    }

    public void setBold(int bold) {
        this.bold = bold;
    }
}
