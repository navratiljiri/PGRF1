package view;

import rasterizer.RasterBufferedImage;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private RasterBufferedImage rasterImage;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public Panel() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        rasterImage = new RasterBufferedImage(WIDTH,HEIGHT);
        rasterImage.setClearColor(Color.BLACK.getRGB());
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        rasterImage.repaint(g);
    }

    public void clear(int color) {
        rasterImage.setClearColor(color);
        rasterImage.clear();
    }

    public RasterBufferedImage getRasterImage() {
        return rasterImage;
    }

}
