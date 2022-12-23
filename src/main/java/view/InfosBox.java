package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Infos;

public final class InfosBox extends JPanel {
    
    private Infos infos;
    private JLabel time = Window.getJLabel("", 15, Color.BLUE),
                   nbWords = Window.getJLabel("", 15, Color.RED);

    public InfosBox(Infos infos) {
        setOpaque(false);
        this.infos = infos;

        setTime(infos.getTime());
        setNbWords(infos.nbWords());

        this.setLayout(new GridLayout(1, 2));
        this.add(Window.getPanel(0, new FlowLayout(), time));
        this.add(Window.getPanel(0, new FlowLayout(), nbWords));
    }

    /*
     * Permet d'augmenter de 1 la variable qui contient le nombre de mots tapés
     * et de mettre à jour le JLabel
     */
    public void addWord() {
        setNbWords(infos.nbWords() + 1);
    }

    /*
     * Modifie le nombre de mots dans le model et met à jour
     * le JLabel
     */
    public void setNbWords(int nbWords) {
        infos.setNbWords(nbWords);
        this.nbWords.setText(nbWords + " words");
    }

    public void removeTime(int seconds) {
        setTime(infos.getTime() - seconds);
    }

    public void setTime(int time) {
        infos.setTime(time);
        this.time.setText(time + "s left");
    }

}