package rasterizer;

public interface Raster {
   int getPixel(int x, int y);

   void setPixel(int x, int y, int color);

   void clear();

   void setClearColor(int color);

   int getWidth();

   int getHeight();

}
