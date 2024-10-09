package rasterizer;

public class LineRasterizerTrivial extends LineRasterizer {

    public LineRasterizerTrivial(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {
        // TODO: dokončit triviální algoritmus

        double k = 0;

        if(x2 - x1 != 0) {
           k = (double) (y2 - y1) / (x2 - x1);
        }

        double q = y1 - k * x1;

        if(Math.abs(y2-y1) < Math.abs(x2-x1)) {
            if(x2 < x1) {
                int temp = x1;
                x1 = x2;
                x2 = temp;

                temp = y1;
                y1 = y2;
                y2 = temp;
            }
        }
        else {
            if(y2 < y1) {
                int temp = x1;
                x1 = x2;
                x2 = temp;

                temp = y1;
                y1 = y2;
                y2 = temp;

            }
        }

        //Když je víc svislá než vodorovná
        if(k > 1) {
            for (double y = y1; y < y2; y++) {
                int x = (int)Math.round((y-q) / k);
                raster.setPixel(x,(int)y,0x000000);
            }
        }
        else {
            if(k != 0 || y2 - y1 == 0) {
                for (double x = x1; x < x2; x++) {
                    int y = (int)Math.round(k*x+q);
                    raster.setPixel((int)x,y,0x000000);
                }
            }
            else {
                for (float y = y1; y < y2; y++) {
                    raster.setPixel(x1,(int)y,0x000000);
                }
            }
        }
    }
}
