package rasterizer;

/**
 * Vykreslení za pomoci triviálního algoritmu
 */
public class LineRasterizerTrivial extends LineRasterizer {

    public LineRasterizerTrivial(Raster raster) {
        super(raster);
    }
    private int changeAxis(double delta, int position1, int position2) {
        return delta < 0 ? position1 : position2;
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {

        double dx = (x2-x1);
        double dy = (y2-y1);

        if(dx == 0) {
            dx = 1;
        }

        double k = dy / dx;
        double q = y1 - k * x1;

        //Když je víc svislá než vodorovná
        if(Math.abs(k) > 1) {
            int startY = changeAxis(dy, y2, y1);
            int endY = changeAxis(dy, y1, y2);
            for (int y = startY; y < endY; y++) {
                int x = (int)Math.round((y-q) / k);
                raster.setPixel(x,y,color);
            }
        }
        else {
            int startX = changeAxis(dx, x2, x1);
            int endX = changeAxis(dx, x1, x2);
                for (int x = startX; x < endX; x++) {
                    int y = (int)Math.round(k*x+q);
                    raster.setPixel(x,y,this.color);
                }
        }
    }
}
