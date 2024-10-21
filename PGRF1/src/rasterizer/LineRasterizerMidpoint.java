package rasterizer;
import java.util.Stack;

/**
 * Midpoint - zásobníková verze
 * Výhody - Protože se půlí úsečky, dochází k vyšší přesnosti
 * Nevýhody - Algoritmus musí stále kontrolovat půlení úseček, opakované výpočty středu, paměťově náročný
 */
public class LineRasterizerMidpoint extends LineRasterizer {

    public LineRasterizerMidpoint(Raster raster) {
        super(raster);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x1,y1,x2,y2});

        while (!stack.isEmpty()) {
            // Odebere vrchol zásobníku a získá souřadnice
            int[] points = stack.pop();

            x1 = points[0];
            y1 = points[1];
            x2 = points[2];
            y2 = points[3];

            //Spočítá střední bod úsečky
            int sx = (x1 + x2) / 2;
            int sy = (y1 + y2) / 2;

            //Vykreslí se pixel na střední hodnotě
            raster.setPixel(sx, sy, color);

            // Pokud je vzdálenost mezi počátečním bodem a středním bodem větší než 1 pixel,
            // přidáme do zásobníku úsek mezi počátečním a středním bodem
            if (Math.abs(x1 - sx) > 1 || Math.abs(y1 - sy) > 1) {
                stack.push(new int[]{x1, y1, sx, sy});
            }
            // Pokud je vzdálenost mezi koncovým bodem a středním bodem větší než 1 pixel,
            // přidáme do zásobníku úsek mezi středním a koncovým bodem
            if (Math.abs(x2 - sx) > 1 || Math.abs(y2 - sy) > 1) {
                stack.push(new int[]{sx, sy, x2, y2});
            }
        }
    }
}
