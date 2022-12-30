package model;

import java.util.ArrayList;
import java.util.List;

public class Word implements Cloneable {

    private String content, pushContent;
    private boolean validate;
    private WordStats stats;

    public Word(String content) {
        this.content = content;
        this.pushContent = "";
        this.stats = new WordStats();
    }
    
    private Word(Word word) {
        this.content = word.content;
        this.pushContent = word.pushContent;
        this.validate = word.validate;
        this.stats = word.stats.clone();
    }

    /**
     * @param letter
     */
    public void pushLetter(char letter) {
        // We don't accept another letter the length of pushContent is already equals
        // to the length of the word.
        // Maybe we will change this in the futur
        if(pushContent.length() == content.length())
            return;
        pushContent += letter;
    }

    public void removeLetter() {
        int len = pushContent.length();
        if(len == 0)
            return;
        pushContent = pushContent.substring(0, len-1);
    }

    /** 
     * @return String
     */
    public String getContent() {
        return content;
    }
    
    /** 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /** 
     * @return String
     */
    public String getPushedContent() {
        return pushContent;
    }

    /** 
     * @param pushContent
     */
    public void pushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public boolean isValidate() {
        return validate;
    }

    public void validate() {
        this.validate = true;
    }

    public class WordStats implements Cloneable {

        private ArrayList<CharStats> charSeq;

        public WordStats() {
            charSeq = new ArrayList<>();
            content.chars().forEach(c -> charSeq.add(new CharStats((char)c)));
        }

        private WordStats(WordStats stats) {
            this.charSeq = new ArrayList<>(stats.charSeq);
        }

        /** Number of good chars in the push word in comparison with the real word.
         *  This function can be useful to generate stats for a player in a game
         * @return int
         */
        public int nbGoodChars() {
            if(content.equals(pushContent))
                return (int)charSeq.stream().filter(c -> !c.erased).count();
            return 0;
        }

        /** Number of errors in the push word in comparison with the real word.
         *  This function can be useful to generate stats for a player in a game
         * @return int
         */
        public int nbErrors() {
            // TODO: Il faut calculer la distance d'édition entre deux mots
            return 0;
        }

        public List<CharStats> getGoodChars() {
            if(content.equals(pushContent))
                return charSeq.stream().filter(c -> !c.erased).toList();
            return new ArrayList<>();
        }

        public WordStats clone() {
            return new WordStats(this);
        }

        public int getWordSize() {
            return this.charSeq.size();
        }

    }

    // On retient le temps a chaque fois qu'un caractère est tapé
    // Si un caractère est éffacer on met erased à true
    // A la fin on regarde tous les mots entièrement bien écrit
    // Pour chacun des mots, on regarde le temps entre chaque caractère utile
    // Sachant qu'un caractère utile est un caractère qui n'a pas été effacé et
    // donc qui n'a pas erased à true. Il faut que erased soit faux

    // On récupère la liste de tous les caractères utiles pour chaque mot
    // On calcule a chaque fois le temps entre deux caractères utiles à la suite

    public static class CharStats {
        private Character c;
        private boolean erased; // A été effacé ou non
        private long time; // temps entre le dernier caractère utile et lui

        public CharStats(Character c) {
            this.c = c;
        }

        public long getTime() {
            return time;
        }

        @Override
        public String toString() {
            return "["+c+", time="+time+"]";
        }

    }

    public void setTimeActualChar(long time) {
        int index = indexActualChar();
        if(index != -1)
            stats.charSeq.get(index).time = time;
    }

    public void erasedActualChar() {
        int index = indexActualChar();
        if(index != -1)
            stats.charSeq.get(index).erased = true;
    }

    private int indexActualChar() {
        int index = pushContent.length() - 1;
        if(index < 0 || index >= content.length())
            return -1;
        return index;
    }

    public WordStats getWordStats() {
        return stats.clone();
    }

    public Word clone() {
        return new Word(this);
    }

}