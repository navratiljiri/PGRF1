package rasterizer;

/**
 * DDA Algoritmus
 * Výhody - nepracujeme se sklonem a posunem - osobně snazší než triviální algoritmus
 */

public class LineRasterizerDDA extends LineRasterizer {
    public LineRasterizerDDA(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {

        double x,y,steps;
        double xc, yc;
        int dx = x2 - x1;
        int dy = y2 - y1;

        if(Math.abs(dx) > Math.abs(dy)) {
            steps = Math.abs(dx);
        }
        else {
            steps = Math.abs(dy);
        }

        xc = dx/steps;
        yc = dy/steps;

        x = x1;
        y = y1;

        for(int i = 0; i <= steps; i++) {
            x += xc;
            y += yc;
            raster.setPixel((int)Math.round(x), (int)Math.round(y), color);
        }

    }
}
