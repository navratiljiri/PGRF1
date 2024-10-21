package rasterizer;

/**
 * DDA Algoritmus
 * Výhody - Nepracujeme se sklonem a posunem
 * Nevýhody - práce s plovoucí čárkou
 */

public class LineRasterizerDDA extends LineRasterizer {
    public LineRasterizerDDA(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {

        double x,y,steps;
        double xc, yc; //přírustky pro každou osu
        int dx = x2 - x1;
        int dy = y2 - y1;

        //Určení, kolik kroků bude zapotřebí pro vykreslení úsečky -> zajistí správné vykreslení úsečky
        if(Math.abs(dx) > Math.abs(dy)) {
            steps = Math.abs(dx);   //pokud je úsečka více horizontální
        }
        else {
            steps = Math.abs(dy);   //pokud je úsečka více vertikální
        }

        //Výpočet přírustků pro každý krok v obou osách -> rozhodují o kolik se posunou souřadnice v jednom kroku
        xc = dx/steps;
        yc = dy/steps;

        //Incializace počátečních souřadnic
        x = x1;
        y = y1;

        for(int i = 0; i <= steps; i++) {
            x += xc;
            y += yc;
            raster.setPixel((int)Math.round(x), (int)Math.round(y), color);
        }

    }
}
