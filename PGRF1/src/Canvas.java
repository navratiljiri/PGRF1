import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas {

    private JFrame frame;
    private JPanel panel;
    private BufferedImage img;

    public Canvas(int width, int height) {
        frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("Canvas");
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
    }

    public void present(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public void drawLine(Line line) {

        float k = 0;

        if(line.getX2() - line.getX1() != 0) {
            k = (line.getY2() - line.getY1()) / (line.getX2() - line.getX1()); //Sklon
        }
        float q = line.getY1() - k * line.getX1(); // Posunutí

        if(Math.abs(line.getY1()) < Math.abs(line.getX1())) {
            if(line.getX2() < line.getX1()) {
              float temp = line.getX1();
              line.setX1(line.getX2());
              line.setX2(temp);
            }
            else {
                if(line.getY2() < line.getY1()) {
                    float temp = line.getY1();
                    line.setY1(line.getY2());
                    line.setY2(temp);
                }
            }
        }

        //Když je víc svislá než vodorovná
        if(k > 1) {
            for (float y = line.getY1(); y < line.getY2(); y++) {
                int x = Math.round((y-q) / k);
                img.setRGB(x,(int)y,line.getColor());
            }
        }
        else {
            if(k != 0) {
                for (float x = line.getX1(); x < line.getX2(); x++) {
                    int y = Math.round(k*x+q);
                    img.setRGB((int)x,y,line.getColor());
                }
            }
            else {
                for (float y = line.getY1(); y < line.getY2(); y++) {
                    img.setRGB((int)line.getX1(),(int)y,line.getColor());
                }
            }
        }
    }
    public void draw(int color) {
        for (int x = 0; x < 100; x++) {
            img.setRGB(x,50, color);
            img.setRGB(50,x, 0xffff11);
            img.setRGB(x,x, 0x11ffff);
        }
        for (int y = 100; y > 0; y--) {
            img.setRGB(y,100-y, 0x11ff11);
        }
    }

    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(new Color(0x000000));
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
    }

    public void start() {
        clear();
        drawLine(new Line(100,10,100,500));
        //drawLine(new Line(100,20,40,100, 0xff00000));
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Canvas(800, 600).start();
            }
        });
    }
}