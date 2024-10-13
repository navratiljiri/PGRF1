package view;

import javax.swing.*;

public class Frame extends JFrame {
    private final Panel panel;

    public Frame() {
        setTitle("Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new Panel();
        add(panel);
        setVisible(true);
        pack();

        panel.setFocusable(true); //abychom uměli vykreslovat do toho panelu, když máme více komponent
        panel.grabFocus();

    }

    public Panel getPanel() {
        return panel;
    }
}
