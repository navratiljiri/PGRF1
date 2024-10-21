package rasterizer;

/**
 * Bresenhamův algoritumus - pouze zatím pro jeden kvadrant
 * Výhody - Nepoužívá plovoucí čárku a dělení, efektivní
 * Nevýhody - Komplikovanější implementace, specifický pro různé kvadranty
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

        prediktor = 2 * (dy - dx);

        raster.setPixel(x,y,0xffffff);
        while(x <= x2) {
            x++;

            if(prediktor < 0) {
                prediktor += 2 * dy;
            }
            else {
                prediktor += 2 * (dx - dy);
                y++;
            }
            raster.setPixel(x,y,0xffffff);
        }
    }
}
