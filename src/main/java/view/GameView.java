package view;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;

import model.Game;
import model.Word;
import model.WordQueue;

public final class GameView extends JPanel {

    private Window win;
    private Game game;
    private GameTextArea textArea;

    public GameView(Window win, Game game) {
        this.win = win;
        this.game = game;
        this.textArea = new GameTextArea();
        
        this.setLayout(new BorderLayout());
        this.add(textArea, BorderLayout.CENTER);
    }

    private final class GameTextArea extends JPanel {

        private WordQueue wordQueue;
        private Queue<WordView> wordViewQueue;
        private WordView actualWord;

        private GameTextArea() {
            this.wordQueue = game.getWordQueue();
            // TEMPORARY
            for(int i=0;i<20;i++)
                wordQueue.addNext(); // On ajoute les premiers mots dans la Queue
            setAndAddWordViewQueue(wordQueue.getQueue());
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
