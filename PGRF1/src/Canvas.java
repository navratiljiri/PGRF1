import Geometry.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Canvas {
    private JFrame frame;
    private JPanel panel;
    private BufferedImage img;

    public Canvas(int width, int height)  {
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
                showInstructions();
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE -> System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void showInstructions() {
        Graphics text = img.getGraphics();
        text.drawString("Close: Esc", img.getWidth() - 80, img.getHeight() - 10);
    }

    /**
     * Render Line
     * @param line
     */
    public void drawLine(Line line) {

        //Když je víc svislá než vodorovná
        if(line.getK() > 1) {
            for (float y = line.getY1(); y < line.getY2(); y++) {
                int x = Math.round((y-line.getQ()) / line.getK());
                img.setRGB(x,(int)y,line.getColor());
            }
        }
        else {
            if(line.getK() != 0 || line.getY2() - line.getY1() == 0) {
                for (float x = line.getX1(); x < line.getX2(); x++) {
                    int y = Math.round(line.getK()*x+ line.getQ());
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

    public void present(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    /**
     * Clear area
     */

    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(new Color(0x000000));
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
    }

    public void start() {
        clear();
        /*drawLine(new Line(200,200,100,100));
        drawLine(new Line(100,200,300,200));
        drawLine(new Line(200,100,200,300));
        drawLine(new Line(200,200,300,100));
        drawLine(new Line(200,200,300,300));
        drawLine(new Line(200,200,100,300));

        drawLine(new Line(200,300,100,200));*/
        drawLine(new Line(100, 100, 110,200));
        panel.repaint();
    }
}
