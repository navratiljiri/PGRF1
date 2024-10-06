import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Canvas(800, 600).start();
            } catch (Exception e) {
                System.out.println("GUI ERROR: "+e);
            }
        });
    }
}