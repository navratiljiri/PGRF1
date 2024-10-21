package view;

import enums.Algorithms;

import javax.swing.*;
import java.awt.*;

public class Help extends JPanel {

    int selectedAlgoritm = 0;
    private final int column1 = 10;
    private final int column2 = 400;
    public Help() {
        setBackground(Color.darkGray);
        setPreferredSize( new Dimension( 800, 150 ) );
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0xffffff));
        g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        g.drawString("Smazat plochu [c]",column1,  20);
        g.drawString("Smazat nejbližší vrchol [r]", column1, 40);
        g.drawString("Změnit algoritmus [a] - " + Algorithms.values()[selectedAlgoritm],column1, 60);
        g.drawString("Zavřít okno [esc]",  column1, 80);
        g.drawString("Přidání nové úsečky [LMB]", column2, 20);
        g.drawString("Editace vrcholu [RMB]", column2, 40);
        g.drawString("Přímá/diagonální/svislá [držet shift]", column2, 60);
        g.drawString("Zvýšit tloušťku - [šipka nahoru]", column2, 80);
        g.drawString("Snížit tloušťku - [šipka dolu]", column2, 100);
    }

    public int getSelectedAlgoritm() {
        return selectedAlgoritm;
    }

    public void setSelectedAlgoritm(int selectedAlgoritm) {
        this.selectedAlgoritm = selectedAlgoritm;
    }
}
