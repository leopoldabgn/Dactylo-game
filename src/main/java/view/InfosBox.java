package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Infos;
import model.Game.GameType;
/**
The InfosBox class represents a graphical component that displays game information such as the time remaining,
the number of correct words typed, and the number of lives or level remaining.

It is initialized with an Infos object and updates its display based on the state of the Infos object.

The InfosBox class also provides methods to update the Infos object and refresh the display.
@see Infos
*/
public final class InfosBox extends JPanel {
    
    private Infos infos;
    private JLabel time = Window.getJLabel("", 15, new Color(124, 181, 24)),
                   nbCorrectWords = Window.getJLabel("", 15, new Color(251, 97, 7)),
                   lifes = Window.getJLabel("", 15, new Color(124, 181, 24)),
                   level = Window.getJLabel("", 15, new Color(239, 71, 111));

    /**

    Constructs an InfosBox object with the specified Infos object.

    The InfosBox object is initialized with the values from the Infos object and displayed in a grid layout.

    The layout depends on the type of game specified in the Infos object.

    @param infos the Infos object to display in the InfosBox
    */
    public InfosBox(Infos infos) {
        setOpaque(false);
        this.infos = infos;

        setTimeLeft((int)(infos.getDuration() / 1000));
        setNbCorrectWords(infos.nbCorrectWords());
        setLifes(this.infos.getLifes());
        setLevel(this.infos.getLevel());

        this.setLayout(new GridLayout(1, 2));
        if(infos.getType() == GameType.CHALLENGE) {
            this.add(Window.getPanel(0, new FlowLayout(), lifes));
            this.add(Window.getPanel(0, new FlowLayout(), level));
        }else {
            this.add(Window.getPanel(0, new FlowLayout(), time));
        }
        this.add(Window.getPanel(0, new FlowLayout(), nbCorrectWords));
    }

    /*
     * Increases the number of correct words by 1 in the Infos object and updates the display.
     */
    public void addWord() {
        infos.updateWordLevelRef();
        infos.setNbWords(infos.nbWords() + 1);
        setNbCorrectWords(infos.nbCorrectWords() + 1);
        // Utils.log("addWord()");
    }

    
    /**
    Sets the number of correct words in the Infos object and updates the display.
    @param nbCorrectWords the new number of correct words
    */
    public void setNbCorrectWords(int nbCorrectWords) {
        infos.setNbCorrectWords(nbCorrectWords);
        this.nbCorrectWords.setText(nbCorrectWords + " words");
    }

    
    /** 
     * @param lifes
     */
    public void setLifes(int lifes) {
        infos.setLifes(lifes);
        this.lifes.setText(lifes + " lifes");
    }

    
    /** 
     * @param level
     */
    public void setLevel(int level) {
        infos.setLevel(level);
        this.level.setText(level + " level");
    }

    public void setStartTime() {
        infos.setStartTime();
        refreshTimeLeft();
    }

    public void refreshTimeLeft() {
        setTimeLeft((int)(infos.getTimeLeft() / 1000));
    }

    
    /** 
     * @param time
     */
    public void setTimeLeft(int time) {
        this.time.setText(time + "s left");
    }

}
