package rasterizer;

/**
 * Bresenhamův algoritumus
 * Výhody - Nepoužívá plovoucí čárku, dělení
 */
public class LineRasterizerBresenham  extends LineRasterizer{
    public LineRasterizerBresenham(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {
        int x,y,prediktor;

        int dx = x2-x1;
        int dy = y2-y1;

        x = x1;
        y = y1;

        prediktor = 2 * dy - dx;

        while(x <= x2) {
            raster.setPixel(x,y,0xffffff);
            x++;

            if(prediktor < 0) {
                prediktor += 2 * dy;
            }
            else {
                prediktor += 2 * (dy) - 2 * - dx;
                y++;
            }
        }
    }
}
