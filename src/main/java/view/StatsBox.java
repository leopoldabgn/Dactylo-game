package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Stats;

public final class StatsBox extends JPanel {
    
    private Stats stats;
    private JLabel time = new JLabel(),
                   nbWords = new JLabel();

    public StatsBox(Stats stats) {
        setOpaque(false);
        this.stats = stats;

        setTime(stats.getTime());
        setNbWords(stats.nbWords());

        this.setLayout(new GridLayout(1, 2));
        this.add(Window.getPanel(0, new BorderLayout(), time));
        this.add(Window.getPanel(0, new BorderLayout(), nbWords));
    }

    /*
     * Permet d'augmenter de 1 la variable qui contient le nombre de mots tapés
     * et de mettre à jour le JLabel
     */
    public void addWord() {
        setNbWords(stats.nbWords() + 1);
    }

    /*
     * Modifie le nombre de mots dans le model et met à jour
     * le JLabel
     */
    public void setNbWords(int nbWords) {
        stats.setNbWords(nbWords);
        this.nbWords = Window.getJLabel(nbWords + " words", 15, Color.RED);
    }

    public void removeTime(int seconds) {
        setTime(stats.getTime() - seconds);
    }

    public void setTime(int time) {
        stats.setTime(time);
        this.time = Window.getJLabel(time + "s left", 15, Color.BLUE);
    }

}
