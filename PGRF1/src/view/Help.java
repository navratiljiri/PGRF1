package view;

import enums.Algorithms;

import javax.swing.*;
import java.awt.*;

public class Help extends JPanel {

    int selectedAlgoritm = 0;
    public Help() {
        setBackground(Color.darkGray);
        setPreferredSize( new Dimension( 800, 150 ) );
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int column1 = 10;
        g.setColor(new Color(0xffffff));
        g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        g.drawString("Smazat plochu [c]",column1,  20);
        g.drawString("Změnit algoritmus [a] - " + Algorithms.values()[selectedAlgoritm],column1, 40);
        g.drawString("Kreslit přímou či diagonální úsečku [držet shift]", column1, 60);
        g.drawString("Zavřít okno [esc]",  column1, 80);
    }

    public int getSelectedAlgoritm() {
        return selectedAlgoritm;
    }

    public void setSelectedAlgoritm(int selectedAlgoritm) {
        this.selectedAlgoritm = selectedAlgoritm;
    }
}
