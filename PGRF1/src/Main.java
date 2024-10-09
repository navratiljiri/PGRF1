import controller.Controller2D;
import view.Frame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Frame frame = new Frame();
                new Controller2D(frame.getPanel());
            } catch (Exception e){
                System.out.println("GUI ERROR: "+e);
            }
        });
    }
}