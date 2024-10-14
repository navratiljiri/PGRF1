package view;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final Panel panel;
    private final Help help;

    public Frame() {
        setTitle("Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new Panel();
        help = new Help();
        add(panel, BorderLayout.CENTER);
        add(help, BorderLayout.SOUTH);
        setVisible(true);
        pack();

        panel.setFocusable(true); //abychom uměli vykreslovat do toho panelu, když máme více komponent
        panel.grabFocus();

    }

    public Panel getPanel() {
        return panel;
    }
    public Help getHelp() {
        return help;
    }
}
