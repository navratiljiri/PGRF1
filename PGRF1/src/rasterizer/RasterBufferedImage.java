package rasterizer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RasterBufferedImage implements Raster {

    private final BufferedImage image;
    private final BufferedImage imagePredicted;
    private int color;
    int x, y;

    public RasterBufferedImage(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        imagePredicted = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void draw(RasterBufferedImage raster) {
        Graphics2D g = image.createGraphics();
        g.setColor(new Color(color));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.drawImage(raster.getImage(), 0, 0, null);
    }

    public void repaint(Graphics g) {
        g.drawImage(image, 0, 0, null);
        g.setColor(Color.white);
        g.drawString("X [ " + x + " ]", 10, 20);
        g.drawString("Y [ " + y + " ]", 10, 30);
    }

    @Override
    public int getPixel(int x, int y) {
        return image.getRGB(x, y);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        if (x < 1 || x >= image.getWidth() || y < 1 || y >= image.getHeight()) {
            return;
        }
        image.setRGB(x, y, color);
    }

    @Override
    public void clear() {
        Graphics g = image.getGraphics();
        g.setColor(new Color(color));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    @Override
    public void setClearColor(int color) {
        Graphics g = image.getGraphics();
        g.setColor(new Color(color));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void createImagePrediction() {
        imagePredicted.setData(image.getData());
    }

    public void setImagePredictionToImage() {
        image.setData(imagePredicted.getData());
    }

    public void setCoordinator(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
