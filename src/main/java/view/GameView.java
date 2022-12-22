package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Game;
import model.Word;
import model.WordQueue;

public final class GameView extends JPanel {

    private Window win;
    private Game game;

    // north panel
    private JLabel gameMode, playerName;

    private GameTextArea textArea;



    public GameView(Window win, Game game) {
        this.win = win;
        this.game = game;
        this.textArea = new GameTextArea();

        gameMode = Window.getJLabel("Game mode: " + game.getType(), 18, Color.WHITE);
        playerName = Window.getJLabel("Player: " + game.getPlayers().get(0).getName(), 18, Color.WHITE);
        
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(getNorthPan(), BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);
    }

    private final class GameTextArea extends JPanel {

        private WordQueue wordQueue;
        private Queue<WordView> wordViewQueue;
        private WordView actualWord;

        private GameTextArea() {
            setOpaque(false);
            setBorder(new EmptyBorder(10, 10, 10, 10));
            //setBorder(new LineBorder(Color.BLACK, 5, true));
            this.wordQueue = game.getWordQueue();
            // TEMPORARY
            for(int i=0;i<20;i++)
                wordQueue.addNext(); // On ajoute les premiers mots dans la Queue
            setAndAddWordViewQueue(wordQueue.getQueue());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
            g.setColor(Color.BLACK);
            
            Graphics2D g2d = (Graphics2D)g;
              //Set  anti-alias!
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON); 

            // Set anti-alias for text
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON); 
            g2d.setStroke(new BasicStroke(5));
            g2d.drawRoundRect(2, 2, getWidth() - 6, getHeight() - 6, 20, 20);
        }

        public void setAndAddWordViewQueue(Queue<Word> queue) {
            removeAll();
            wordViewQueue = new LinkedList<>();
            queue.stream().forEach((word) -> {
                WordView w = new WordView(word);
                wordViewQueue.add(w);
                this.add(w);
            });
            actualWord = wordViewQueue.peek();
            revalidate();
            repaint();
        }

        /**
         * Permet de prendre le focus sur le mot suivant
         */
        public WordView nextWord() {
            actualWord = wordViewQueue.poll();
            if(actualWord == null)
                return null;
            game.nextWord();
            return actualWord;
        }

        public void nextWords() {
            // On fait 15 ou 20 next ? On change les 20 mots affichés d'un coup ?
            /*
            for(int i=0;i<20;i++)
                wordQueue.addNext();
            setWordViewQueue(wordQueue.getQueue());
            ....
            revalidate();
            repaint();
            */
        }

        public WordView getActualWord() {
            return actualWord;
        }

    }

    private JPanel getNorthPan() {
        JPanel north = new JPanel();
        north.setOpaque(false);
        north.setLayout(new BorderLayout());
        north.setBorder(new EmptyBorder(0, 0, 14, 0));

        JLabel title = HomeView.getTitle("Dactylo Game", 30);
        title.setVerticalAlignment(JLabel.CENTER);

        playerName.setVerticalAlignment(JLabel.CENTER);

        gameMode.setVerticalAlignment(JLabel.CENTER);
        // title.setPolice, setColor....
        north.add(title, BorderLayout.CENTER);
        north.add(playerName, BorderLayout.WEST);
        north.add(gameMode, BorderLayout.EAST);

        return north;
    }

    private JPanel getSouthPan() {
        // TODO: Write getSouthPan...
        return null;
    }

    public void nextWord() {
        textArea.nextWord();
    }

    /** 
     * Maybe we should return a copy here ?
     * @return Game
     */
    public Game getGame() {
        return game;
    }

    public WordView getActualWord() {
        return textArea.getActualWord();
    }

}
